//页面加载时调用
//utf-8
const path = $("#path").val();
var totalRecord, currentPage;
$(function () {
    jumpToPage(1);
});

function jumpToPage(pn) {
    console.log("-------------s------${APP_PATH}--------" + path);
    $.ajax({
        url: path + "/emps",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            console.log(result);
            //1.解析并显示员工数据
            build_emps_table(result);
            //2.解析并显示分页数据
            build_page_info(result);
            //3.解析并显示分页条
            build_page_nav(result);
        }
    })
}

function build_emps_table(result) {
    //清空表格内容
    $("#emps_table tbody").empty();
    //重置全选按钮状态
    $("#check_all").prop("checked",false);
    var emps = result.data.pageInfo.list;
    $.each(emps, function (index, item) {
        var checkBoxId = $("<td><input type='checkbox' class='check_item'/></td>");
        var empIdId = $("<td></td>").append(item.empId);
        var empNameId = $("<td></td>").append(item.empName);
        var empGenderId = $("<td></td>").append(item.gender);
        var empEmailId = $("<td></td>").append(item.email);
        var empDepartmentNameId = $("<td></td>").append(item.department.deptName);

        var btnEditId = $("<button></button>").addClass("btn btn-primary btn-sm  edit_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
        btnEditId.attr("edit_id", item.empId);
        var btnDeleteId = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
        btnDeleteId.attr("del_emp_id", item.empId).attr("del_emp_name", item.empName);

        var btnTd = $("<td></td>").append(btnEditId).append(" ").append(btnDeleteId);
        $("<tr></tr>").append(checkBoxId)
            .append(empIdId)
            .append(empNameId)
            .append(empGenderId)
            .append(empEmailId)
            .append(empDepartmentNameId)
            .append(btnTd)
            .appendTo("#emps_table tbody");
    })
}

function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前第" + result.data.pageInfo.pageNum + "页，" +
        "共" + result.data.pageInfo.pages + "页 " + result.data.pageInfo.total + "条数据");
    totalRecord = result.data.pageInfo.total;
    currentPage = result.data.pageInfo.pageNum;
}

function build_page_nav(result) {
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.data.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            jumpToPage(1);
        });
        prePageLi.click(function () {
            jumpToPage(result.data.pageInfo.pageNum - 1);
        });
    }
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.data.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        lastPageLi.click(function () {
            jumpToPage(result.data.pageInfo.pages);
        });
        nextPageLi.click(function () {
            jumpToPage(result.data.pageInfo.pageNum + 1);
        });
    }

    ul.append(firstPageLi).append(prePageLi);
    $.each(result.data.pageInfo.navigatepageNums, function (index, item) {
        var pageLiId = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
        if (result.data.pageInfo.pageNum == item) {
            pageLiId.addClass("active");
        }
        pageLiId.click(function () {
            jumpToPage(item);
        });
        ul.append(pageLiId);
    });
    ul.append(nextPageLi).append(lastPageLi);
    var navId = $("<nav></nav>").append(ul);
    navId.appendTo("#page_nav_area");
}

//新增员工
function showAddEmpsDialog() {
    //清空之前校验的数据
    reset_form("#empAddModal form");
    //点击新增时获取部门信息
    getDepts("#empAddModal select");
    $("#empAddModal").modal({
        backdrop: "static"
    })
}

//清空校验信息
function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-success has-error");
    $(ele).find(".help-block").text("");

}

//获取部门信息
function getDepts(ele) {
    $(ele).empty();
    $.ajax({
        url: path + "/dept/depts",
        type: "GET",
        success: function (result1) {
            console.log(result1);
            $.each(result1.data.depts, function () {
                var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                optionEle.appendTo(ele);
            })
        }
    })
}

//保存员工
function saveEmployee() {
    //校验数据
    if (!validate_add_form()) {
        //校验不通过
        return false;
    }
    //接口校验结果,只校验的是员工姓名
    var msg = $("#emp_save_btn").attr("ajax-va");
    console.log("--ajax-va---" + msg);
    if (msg == "success") {
    } else {
        show_validate_msg("#empName_add_input", "error", msg);
        return false;
    }

    $.ajax({
        url: path + "/saveEmp",
        type: "POST",
        data: $("#empAddModal form").serialize(),
        success: function (result) {
            if (200 == result.code) {
                $("#empAddModal").modal("hide");
                jumpToPage(totalRecord);
            } else {
                if (undefined != result.data.errorFields.empName) {
                    show_validate_msg("#email_add_input", "error", result.data.errorFields.empName);
                }
                if (undefined != result.data.errorFields.email) {
                    show_validate_msg("#email_add_input", "error", result.data.errorFields.email);
                }
            }
        }
    })
}

//校验数据
function validate_add_form() {
    const empName = $("#empName_add_input").val();
    //用户名可以是2-5位中文或者6-16位英文和数字的组合;
    const regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
    if (regName.test(empName)) {
        // alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
        show_validate_msg("#empName_add_input", "success", "");
    } else {
        show_validate_msg("#empName_add_input", "error", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
        return false;
    }
    //邮箱校验
    const email = $("#email_add_input").val();
    const regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    if (regEmail.test(email)) {
        show_validate_msg("#email_add_input", "success", "");
    } else {
        show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
        return false;
    }
    return true;
}

//显示校验结果信息
function show_validate_msg(ele, status, msg) {
    //清除之前校验状态
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("111");
    if ("success" == status) {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
    $(ele).next("span").text(msg);
}

//输入用户名的时候校验
$("#empName_add_input").change(function () {
    var empName = this.value;
    $.ajax({
        url: path + "/checkEmpName",
        data: "empName=" + empName,
        type: "POST",
        success: function (result) {
            if (200 == result.code) {
                show_validate_msg("#empName_add_input", "success", "用户名可用");
                $("#emp_save_btn").attr("ajax-va", "success");
            } else {
                show_validate_msg("#empName_add_input", "error", result.msg);
                $("#emp_save_btn").attr("ajax-va", result.msg);
            }
        }
    })
});

//编辑按钮点击
$(document).on("click", ".edit_btn", function () {

    //获取部门信息
    getDepts("#empUpdateModal select");
    //根据id获取员工信息
    getEmp($(this).attr("edit_id"));

    $("#empUpdateModal").modal({
        backdrop: "static"
    })
});

//获取员工信息
function getEmp(empId) {
    $.ajax({
        url: path + "/getEmp",
        data: "empId=" + empId,
        type: "POST",
        success: function (result) {
            if (result.code == 200) {
                var empData = result.data.data;
                $("#empName_update_static").text(empData.empName);
                $("#email_update_input").val(empData.email);
                $("#empUpdateModal input[name=gender]").val([empData.gender]);
                // $("#empUpdateModal select").val(empData.dId);
                $("#dId").val(empData.dId);
                $("#emp_update_btn").attr("edit_emp_id", empData.empId);
            }
        }
    })
}


//更新按钮点击
$("#emp_update_btn").click(function () {
    //1、校验邮箱信息
    var email = $("#email_update_input").val();
    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    if (!regEmail.test(email)) {
        show_validate_msg("#email_update_input", "error", "邮箱格式不正确");
        return false;
    } else {
        show_validate_msg("#email_update_input", "success", "");
    }
    //
    console.log("-----------:" + $(this).attr("edit_emp_id"))
    $.ajax({
        url: path + "/updateEmp/" + $(this).attr("edit_emp_id"),
        data: $("#empUpdateModal form").serialize(),
        type: "PUT",
        success: function (result) {
            if (200 == result.code) {
                $("#empUpdateModal").modal("hide");
                jumpToPage(currentPage);
            }
        }
    })
});


//单个删除
// $("#delete_btn").click(function () {
$(document).on("click", ".delete_btn", function () {
    var delEmpId = $(this).attr("del_emp_id");
    var delEmpName = $(this).attr("del_emp_name");
    if (confirm("确认删除" + delEmpName + "吗？")) {
        $.ajax({
            url: path + "/deleteEmp",
            data: "empIds=" + delEmpId,
            type: "POST",
            success: function (result) {
                alert(result.msg);
                jumpToPage(currentPage);
            }
        })
    }
});

//批量删除
function deleteAllChecked() {
    // confirm("批量删除")
    var empIds = "";
    var empName = "";
    $.each($(".check_item:checked"), function () {
        empName += $(this).parents("tr").find("td:eq(2)").text() + ",";
        empIds += $(this).parents("tr").find("td:eq(1)").text() + "-";
    });
    empIds.substring(0, empIds.length - 1);
    empName.substring(0, empName.length - 1);
    if (confirm("确定删除员工【" + empName + "】吗？")) {
        console.log("------------s---------------" + empName);
        $.ajax({
            url: path + "/deleteEmp",
            data: "empIds=" + empIds,
            type: "POST",
            success: function (result) {
                alert(result.msg);
                if (result.code == 200) {
                    jumpToPage(currentPage);
                }
            }
        })
    }
}

//点击全选或者全不选
$("#check_all").click(function () {
    // console.log("-----:" + $("#check_all").prop("checked"));
    $(".check_item").prop("checked", $("#check_all").prop("checked"));
});

//单个点击选中
$(document).on("click", ".check_item", function () {
    var flag = $(".check_item:checked").length == $(".check_item").length;
    console.log("-----2------:" + flag);
    $("#check_all").prop("checked", flag);
});
//
// $("#check_item").click(function () {
//     console.log("-----1------");
//     var flag = $("#check_item:checked").length == $("#check_item").length;
//     console.log("-----2------:" + flag);
//     $("#check_all").prop("checked", flag);
//
// });
var properties = {
    //ajax url 和类型
    tableAjax : {
        url : '/rds_user/findRdsUserList',
        type : "GET",
    },
    //table的html id
    tableId : 'rds_user',//表的id
    uniqueId : 'userId',//表的主键
    //table的列
    tableColumn : [ {
        checkbox : true,
        align : 'center'
    }, {
        field : 'userId',
        title : '用户ID'
    }, {
        field : 'username',
        title : '用户名'
    }, {
        field : 'department',
        title : '所属部门'
    }, {
        field : 'job',
        title : '职业'
    }, {
        field : 'createTime',
        title : '添加时间'
    }, {
        field : 'updateTime',
        title : '修改时间'
    }, {
        field : 'isUse',
        title : '是否启用',
        formatter: function (value, row, index){
            if(value==0){
                return "未启用";
            }else if(value==1){
                return "启用";
            }
        }
    }, ]
};
$(function() {
    //初始化Table
    var oTable = new TableInit(properties);
    oTable.Init();
});



//新增
function toAddRdsUserById() {
    Dialog.modal({url:"/rds_user/toAddRdsUserById"});
}

//新增
function toUpdateRdsUserById() {
    //获取所有被选中的记录
    var rows = $("#rds_user").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }
    Dialog.modal({url:"/rds_user/toUpdateRdsUserById?userId="+rows[0]['userId']});
}

//删除
function deleteRdsUserByIds() {
    //获取所有被选中的记录
    var rows = $("#rds_user").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请先选择要删除的记录!');
        return;
    }
    var userIds = {};
    for (var i = 0; i < rows.length; i++) {
        userIds[i] = rows[i]['userId'];
    }
    Dialog.confirm({
        message : "确认要删除选择的数据吗？"
    }).on(function(e) {
        if (!e) {
            return;
        }
        $.ajax({
            type : "POST",
            url : '/rds_user/deleteRdsUserByIds',
            data : {
                userIds : userIds
            },
            dataType : 'json',
            success : function(data) {
                if (data.code == "0") {
                    toastr.success('删除用户成功!');
                    search();
                } else {
                    toastr.error("删除用户失败!");
                }
            }
        });
    });
}


//修改密码
function updatePasswordById() {
    //获取所有被选中的记录
    var rows = $("#rds_user").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }
    var userId = rows[0]['userId'];
    Dialog.confirm({
        message : "确认要重置密码吗？"
    }).on(function(e) {
        if (!e) {
            return;
        }
        $.ajax({
            async : false,
            type : "POST",
            url : '/rds_user/revertPassword',
            data : {
                userId : userId,
            },
            dataType : 'json',
            success : function(data) {
                if (data.code == "0") {
                    toastr.success('密码重置成功!');
                    search();
                } else {
                    toastr.error("密码重置失败!");
                }
            },
            error : function(data) {
                toastr.error("密码重置失败!");
            }
        });
    });

}

//修改密码
function setRdsRoleById() {
    //获取所有被选中的记录

    var rows = $("#rds_user").bootstrapTable('getSelections');
    if (rows.length == 0) {
        toastr.warning('请选择一条记录!');
        return;
    } else if (rows.length > 1) {
        toastr.warning('最多选择一条记录!');
        return;
    }

    var userId = rows[0]['userId'];
    Dialog.modal({
        url : '/rds_user/setRdsRoleByUserId?userId=' + userId
    });
}

function search() {
    $("#rds_user").bootstrapTable('refreshOptions',{pageNumber:1});
}

function resetForm() {
    searchForm.userId.value = "";
    searchForm.username.value = "";
    search();
}


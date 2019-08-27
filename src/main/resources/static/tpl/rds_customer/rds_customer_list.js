var properties = {
    //ajax url 和类型
    tableAjax : {
        url : '/rds_customer/findRdsCustomerList',
        type : "GET",
    },
    //table的html id
    tableId : 'rds_customer',//表的id
    uniqueId : 'id',//表的主键
    //table的列
    tableColumn : [ {
        checkbox : true,
        align : 'center'
    }, {
        field : 'id',
        title : '客户ID'
    }, {
        field : 'name',
        title : '客户名'
    }, {
        field : 'hobbies',
        title : '客户爱好'
    }, {
        field : 'gender',
        title : '客户性别'
    }, ]
};
$(function() {
    //初始化Table
    var oTable = new TableInit(properties);
    oTable.Init();
});

function search() {
    $("#rds_customer").bootstrapTable('refreshOptions',{pageNumber:1});
}

function resetForm() {
    searchForm.id.value = "";
    searchForm.name.value = "";
    search();
}

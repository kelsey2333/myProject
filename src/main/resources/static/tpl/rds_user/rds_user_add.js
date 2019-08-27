function addRdsUser() {
        $('#addForm').data('bootstrapValidator').validate();
        if (!($('#addForm').data('bootstrapValidator').isValid())) {
            return;
        }
        $.mask_element('#addForm','数据保存中...');
        $.ajax({
            url : '/rds_user/addRdsUser',
            data : $("#addForm").serialize(),
            success : function(result) {
                //请求成功时
                toastr.success('增加用户成功!');
                $(".close").click();
                search();
            }
        })

    }


$(function() {
    //初始化表单验证
    formValidator();
});
//初始化表单验证
function formValidator() {
    $('#addForm').bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
            username : {
                validators : {
                    notEmpty : {
                        message : '姓名不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 50,
                        message: '姓名不能超过50位'
                    }
                }
            },
            job : {
                validators : {
                    notEmpty : {
                        message : '职业不能为空'
                    }
                }
            },
        }
    });
}
function updateRdsUser() {
        $('#updateForm').data('bootstrapValidator').validate();
        if (!($('#updateForm').data('bootstrapValidator').isValid())) {
            return;
        }
        $.mask_element('#updateForm','数据保存中...');
        $.ajax({
            url : '/rds_user/updateRdsUser',
            data : $("#updateForm").serialize(),
            success : function(result) {
                //请求成功时
                toastr.success('修改用户成功!');
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
    $('#updateForm').bootstrapValidator({
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
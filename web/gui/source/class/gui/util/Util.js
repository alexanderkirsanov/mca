qx.Class.define("gui.util.Util",
    {
        extend:qx.core.Object,

        members:{
            isValid:function (item) {
                if (item.getValue().length == 0) {
                    var errorMessage = qx.locale.Manager.tr("%1 is Empty.", value);
                    throw new qx.core.ValidationError("Validation Error", errorMessage);
                }
            }
        }
    }
);

/**
 * Created by IntelliJ IDEA.
 * User: akirsanov
 * Date: 04.03.12
 * Time: 2:17
 * To change this template use File | Settings | File Templates.
 */
qx.Class.define("gui.RepositoryManager",
    {
        extend:qx.ui.window.Window,

        construct:function () {
            this.base(arguments);
            var layout = new qx.ui.layout.Grid(9, 5);
            layout.setColumnAlign(0, "right", "top");
            layout.setColumnAlign(2, "right", "top");
            /* Container widget */
            this.setLayout(layout);

            /* Labels */
            var labels = ["Name", "Password", "Url", "Project"];
            for (var i = 0; i < labels.length; i++) {
                this.add(new qx.ui.basic.Label(labels[i]).set({
                    allowShrinkX:false,
                    paddingTop:3
                }), {row:i, column:0});
            }
            this.__manager = new qx.ui.form.validation.Manager();

            var lengthValidator = function (value, item) {
                var valid = value != null && value.length > 2;
                if (!valid) {
                    item.setInvalidMessage("Please enter a text at with least 3 characters.");
                }
                return valid;
            };


            this.__nameField = new qx.ui.form.TextField();
            this.__passField = new qx.ui.form.PasswordField();
            this.__urlField = new qx.ui.form.TextField();
            this.__projectField = new qx.ui.form.TextField();
            this.__manager.add(this.__nameField, lengthValidator);
            this.__manager.add(this.__passField, lengthValidator);
            this.__manager.add(this.__urlField, lengthValidator);
            this.__manager.add(this.__projectField, lengthValidator);


            this.add(this.__nameField.set({
                allowShrinkX:false,
                paddingTop:3
            }), {row:0, column:1});

            this.add(this.__passField.set({
                allowShrinkX:false,
                paddingTop:3
            }), {row:1, column:1});

            this.add(this.__urlField.set({
                allowShrinkX:false,
                paddingTop:3
            }), {row:2, column:1});

            this.add(this.__projectField.set({
                allowShrinkX:false,
                paddingTop:3
            }), {row:3, column:1});

            /* Button */
            var button1 = this.__addButton = new qx.ui.form.Button("Add");
            button1.setAllowStretchX(false);

            this.add(
                button1,
                {
                    row:5,
                    column:1
                }
            );
            this.__manager.setValidator(function (items) {
                return true;
            });

            button1.addListener("execute", this.checkInput, this);
            this.addListener("appear", this.__prepareEffect, this);

        },
        members:{
            __effect:null,
            __container:null,
            __addButton:null,
            __manager:null,
            __nameField:null,
            __passField:null,
            __urlField:null,
            __projectField:null,

            checkInput:function () {
                if (!this.__manager.validate()) {
                    this.__effect.start();
                } else {
                    //{"url":"url","projectName":"projectName","userName":"userName","password":"pass"}
                    var sendObject = {"url":this.__urlField.getValue(), "projectName":this.__projectField.getValue(), "userName":this.__nameField.getValue(), "password":this.__passField.getValue()};
                    var req = new qx.io.remote.Request();
                    req.setUrl("http://localhost:8080/rest/service/project");
                    req.setMethod("POST");
                    req.setData(JSON.stringify(sendObject));
                    req.setRequestHeader("Content-Type","application/json");
                    req.send();
                }
            },


            __prepareEffect:function () {
                this.__effect = new qx.fx.effect.combination.Shake(this.getContainerElement().getDomElement());
            }
        }
    })
;
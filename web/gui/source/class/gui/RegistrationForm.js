/**
 * Created with IntelliJ IDEA.
 * User: akirsanov
 * Date: 07.04.12
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */

qx.Class.define("gui.RegistrationForm",{
        extend:qx.ui.window.Window,
        construct:function(){
            this.base(arguments);
            var layout = new qx.ui.layout.Flow()
            this.setLayout(layout);
            var groupBox = new qx.ui.groupbox.GroupBox("Registration");
            groupBox.setWidth(200);
            this.add(groupBox);
            var grid = new qx.ui.layout.Grid();
            grid.setSpacing(5);
            grid.setColumnAlign(0, "left", "middle");
            groupBox.setLayout(grid);
            var nameLabel = new qx.ui.basic.Label("Name:");
            groupBox.add(nameLabel, {row: 0, column: 0});
            var nameTextfield = new qx.ui.form.TextField();
            groupBox.add(nameTextfield, {row: 0, column: 1});
            var passwordLabel = new qx.ui.basic.Label("Password:");
            groupBox.add(passwordLabel, {row: 1, column: 0});
            var passwordField = new qx.ui.form.PasswordField();
            groupBox.add(passwordField, {row: 1, column: 1});
            var registrationLabel = new qx.ui.basic.Label("New User:");
            groupBox.add(registrationLabel, {row: 2, column: 0});
            var registrationCheckbox = new qx.ui.form.CheckBox();
            groupBox.add(registrationCheckbox, {row: 2, column: 1});
            var actionButton = new qx.ui.form.Button("Login/Register");
            groupBox.add(actionButton, {row: 5, column: 0});
            registrationCheckbox.addListener("click", function(){

                var expertLabel = new qx.ui.basic.Label("Expert:");
                groupBox.add(expertLabel, {row: 3, column: 0});
                var expertCheckbox = new qx.ui.form.CheckBox();
                groupBox.add(expertCheckbox, {row: 3, column: 1});
            },this);

        }
    }
);


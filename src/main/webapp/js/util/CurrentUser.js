'use strict';
app.factory('CurrentUser', [
    'CrudUtil',
    '$location',
    function (CrudUtil, $location) {
        var User = {};

        User.currentUser = '';
        User.operations = [];

        User.getCurrentUser = function () {
            if (User.currentUser != undefined && User.currentUser != '')
                return User.currentUser;
            else
                return CrudUtil.customGetService('Profile', 'getCurrentUser')
                    .then(function (data) {
                    	
                        if (data) {
                            User.currentUser = data;
                            
                            
                            for (var i = 0; i < data.roles.length; i++) {
								var role = data.roles[i];

								for (var j = 0; j < role.operations.length; j++) {
									User.operations
											.push(role.operations[j].name)
								}
							}

                            User.operations = User.operations
									.filter(function(value, index, self) {
										return self.indexOf(value) === index;
									});

                            
                            return User.currentUser;
                        } else
                            window.location.href = "/form/login.html";
                    }, function (err) {
                        console.log(err);
                    });

        }
        User.checkAuthority = function (role1, role2) {
            if (!User.currentUser) {
                $location.path("");
            }
            if (role1 == "") return User;
            if (User.currentUser != "") {
                if (User.currentUser[role1] || (role2 && User.currentUser[role2]))
                    return User;
                else
                    $location.path("403");
            }

            else {
                $location.path("");
            }

        }

        User.canDo = function (opr) {
            return User.operations.includes(opr);
        }
        User.loadSecPage = function (opr) {
            if (!User.operations.includes(opr))
                window.location.href = "../form/codePage/403.html";
        }
        return User;
    }]);
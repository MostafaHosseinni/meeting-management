app.controller("serviceTypeListController",
		function($scope, Labels, UiUtil, CurrentUser, CrudUtil) {

			$scope.preload = function() {

				$scope.Labels = Labels;

				$scope.titlePage = Labels.Generals.list + " "
						+ Labels.ServiceType.main;

				$scope.entityName = "ServiceType";

				$scope.tableModel = UiUtil.getDefaultTableModel($scope,
						$scope.entityName);

				$scope.tableModel.columns = [
						UiUtil.getDefaultColumn(Labels.ServiceType.title,
								"title"),
						UiUtil.getDefaultColumn(Labels.ServiceType.expireDate,
								"expireDate") ];

			}

			$scope.load = function() {
				$scope.preload();
			}

			$scope.addItem = function() {
				$scope.modelData = {};
			}

			$scope.load();

			$scope.validate = function() {

				if ($scope.modelForm.$error) {
					if ($scope.modelForm.$error.required) {
						alertWarning(Labels.Warning.fillForm);
						return false;
					} else if ($scope.modelForm.$error.pattern) {
						alertWarning(Labels.Warning.dataNotSuccess);

						return false;
					}
				}

				return true;
			}

			$scope.saveOrUpdate = function() {

				if (!$scope.validate()) {
					return;
				}
				if (!$scope.modelData.id) {
					CrudUtil.save($scope.entityName, $scope.modelData).then(
							function(response) {
								alertSuccess(Labels.Success.save);
								$scope.afterSaveOrUpdateSuccessful(true);
							});
				} else {
					CrudUtil.update($scope.entityName, $scope.modelData).then(
							function(response) {
								alertSuccess(Labels.Success.update);
								$scope.afterSaveOrUpdateSuccessful(false);
							});
				}

			}

			$scope.afterSaveOrUpdateSuccessful = function(isSave) {
				$scope.modelData = {};
				$('#' + $scope.entityName + 'View').modal('hide');
				$scope.tableModel.callService();
			}
		});
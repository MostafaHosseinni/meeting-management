app.controller("approvalsDetailController",
		function($scope, Labels, UiUtil, CrudUtil, $uiRouter, OperationUtil,
				$location) {

			$scope.preload = function() {

				$scope.Labels = Labels;

				$scope.id = $uiRouter.globals.params.id;

				$scope.modelData = {};

				$scope.titlePage = Labels.Generals.list + " "
						+ Labels.Approvals.main;

				$scope.entityName = "Approvals";

				CrudUtil.getById('Approvals', $scope.id).then(
						function(response) {
							$scope.modelData = response;

						});

				$scope.tableModel = UiUtil.getDefaultClientTableModel($scope,
						"Agenda");
				$scope.tableModel.pageSize = 5;
				$scope.tableModel.columns = [
						UiUtil.getDefaultColumn(Labels.Agenda.ruls, "ruls"),
						UiUtil.getDefaultColumn(Labels.Agenda.approvals,
								"approvals"), ];
				$scope.tableModel.operations[0] = OperationUtil
						.getDefaultIconOperation('fa fa-trash', function(
								element, entityName) {

						});
				
				$scope.tableModel.hasPaging = false;
				$scope.tableModel.rowTitle = "#";
				$scope.tableModel.operationTitle = Labels.Generals.operation;
				$scope.inviteesModel = UiUtil.getDefualtAutoCompleteModel(
						"Profile", "userName", "userName");
			}
			$scope.load = function() {
				$scope.preload();
			}

			$scope.addItem = function() {
				$scope.modelData = {};
			}

			$scope.load();

		});

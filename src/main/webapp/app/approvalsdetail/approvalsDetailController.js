app.controller("approvalsDetailController",
		function ($scope, Labels, UiUtil, CrudUtil,
				$uiRouter, OperationUtil, $location) {

				$scope.preload = function () {

					$scope.Labels = Labels;

					$scope.id = $uiRouter.globals.params.id;

					$scope.modelData = {};
					$scope.modelData.approvalFile = {};
					$scope.modelData.meeting = {};





					$scope.titlePage = Labels.Generals.list + " " +
						Labels.Approvals.main;

					$scope.entityName = "Approvals";
					
					CrudUtil.getById('Meeting', $scope.id).then(function (response) {
						$scope.modelData.meeting = response;

					});
					
				}
	$scope.load = function() {
		$scope.preload();
	}

	$scope.addItem = function() {
		$scope.modelData = {};
	}

	$scope.load();


});

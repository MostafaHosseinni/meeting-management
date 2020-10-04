app.controller("approvalsViewController", function($scope, Labels, UiUtil, $location) {

	$scope.preload = function() {

		$scope.Labels = Labels;

		$scope.titlePage = Labels.Generals.list + " " + Labels.Approvals.main;

		$scope.entityName = "Approvals";

		$scope.tableModel = UiUtil.getDefaultTableModel($scope,
				$scope.entityName);

		$scope.tableModel.columns = [
				UiUtil.getDefaultColumn(Labels.Meeting.title, "meeting.title"),
				UiUtil.getDefaultColumn(Labels.Meeting.meetingDate,
						"meeting.meetingDate"),
				UiUtil.getDefaultColumn(Labels.Meeting.boss, "boss.userName")];
		
		$scope.tableModel.dataService = "getAllApprovalsForCurrentUser";

		$scope.tableModel.operations[0].action = function(element , entityName) {
			$location.path('approvalsDetail/' + element.id);

		}
	}
	

	$scope.load = function() {
		$scope.preload();
	}

	$scope.addItem = function() {
		$scope.modelData = {};
	}

	$scope.load();

});

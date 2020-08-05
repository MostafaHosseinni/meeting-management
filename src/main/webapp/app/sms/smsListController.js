app.controller("smsListController", function($scope, Labels, UiUtil,
		CurrentUser, CrudUtil) {

	$scope.preload = function() {

		$scope.Labels = Labels;

		$scope.titlePage = Labels.Generals.list + " " + Labels.Sms.main;

		$scope.entityName = "Sms";

		$scope.tableModel = UiUtil.getDefaultTableModel($scope,
				$scope.entityName);

		$scope.smsStatusModel = UiUtil.getDefaultLocalComboModel([
				UiUtil.getDefaultComboLocalData(Labels.SmsStatus.NEW,
						Labels.SmsStatus.NEW),
				UiUtil.getDefaultComboLocalData(Labels.SmsStatus.SENT,
						Labels.SmsStatus.SENT),
				UiUtil.getDefaultComboLocalData(Labels.SmsStatus.FAILED,
						Labels.SmsStatus.FAILED), ]);

		$scope.tableModel.columns = [
				UiUtil.getDefaultColumn(Labels.Sms.description, "description"),
				UiUtil.getDefaultColumn(Labels.Sms.receiverNumber,
						"receiverNumber"),
				UiUtil.getDefaultColumn(Labels.Sms.smsStatus, "smsStatus") ];

	}

	$scope.load = function() {
		$scope.preload();
	}

	$scope.addItem = function() {
		$scope.modelData = {};
	}

	$scope.load();

	$scope.validate = function() {
		return $scope.modelForm.$valid;
	}

	$scope.saveOrUpdate = function() {

		if (!$scope.validate()) {
			alertWarning(Labels.Warning.fillForm);
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

app.controller("meetingListController", function ($scope, Labels, UiUtil,
	CurrentUser, CrudUtil, OperationUtil) {

	$scope.preload = function () {

		$scope.Labels = Labels;

		$scope.titlePage = Labels.Generals.list + " " + Labels.Meeting.main;

		$scope.entityName = "Meeting";

		$scope.tableModel = UiUtil.getDefaultTableModel($scope,
			$scope.entityName);

		$scope.meetingStatusModel = UiUtil.getDefaultLocalComboModel([
			UiUtil.getDefaultComboLocalData(Labels.MeetingStatus.NEW,
				Labels.MeetingStatus.NEW),
			UiUtil.getDefaultComboLocalData(Labels.MeetingStatus.CONFIRMED,
				Labels.MeetingStatus.CONFIRMED),
			UiUtil.getDefaultComboLocalData(Labels.MeetingStatus.REJECT,
				Labels.MeetingStatus.REJECT),]);
		$scope.meetingTypeModel = UiUtil.getDefaultLocalComboModel([
			UiUtil.getDefaultComboLocalData(Labels.MeetingType.MEETING,
				Labels.MeetingType.MEETING),
			UiUtil.getDefaultComboLocalData(Labels.MeetingType.APPOINMENT,
				Labels.MeetingType.APPOINMENT),]);
		$scope.meetingPositionModel = UiUtil.getDefaultLocalComboModel([
			UiUtil.getDefaultComboLocalData(Labels.MeetingPosition.HOST,
				Labels.MeetingPosition.HOST),
			UiUtil.getDefaultComboLocalData(Labels.MeetingPosition.GUEST,
				Labels.MeetingPosition.GUEST),]);

		$scope.tableModel.columns = [
			UiUtil.getDefaultColumn(Labels.Meeting.title, "title"),
			UiUtil
				.getDefaultColumn(Labels.Meeting.createDate,
					"createDate"),
			UiUtil.getDefaultColumn(Labels.Meeting.lastModifiedDate,
				"lastModifiedDate"),
			UiUtil.getDefaultColumn(Labels.Meeting.meetingDate,
				"meetingDate"),
			UiUtil.getDefaultColumn(Labels.Meeting.startTime, "startTime"),
			UiUtil.getDefaultColumn(Labels.Meeting.endTime, "endTime"),
			UiUtil.getDefaultColumn(Labels.Meeting.meetingStatus,
				"meetingStatus"),
			UiUtil.getDefaultColumn(Labels.Meeting.meetingType,
				"meetingType"),
			UiUtil.getDefaultColumn(Labels.Meeting.meetingPosition,
				"meetingPosition"),
			UiUtil.getDefaultColumn(Labels.Meeting.meetingService,
				"meetingService"),
			UiUtil.getDefaultColumn(Labels.Meeting.approvals,
				"approvals.id"),
			UiUtil.getDefaultColumn(Labels.Meeting.meetingRoom,
				"meetingRoom.id")];
		$scope.approvalsModel = UiUtil.getDefualtAutoCompleteModel("Approvals",
			"id", "id");
		$scope.meetingRoomModel = UiUtil.getDefualtAutoCompleteModel(
			"MeetingRoom", "roomName", "roomName");

		$scope.inviteesModel = UiUtil.getDefualtAutoCompleteModel("Profile",
			"userName", "userName");
		$scope.inviteesModel.serviceName = "getAllInvitees";


	}



	$scope.load = function () {
		$scope.preload();
	}

	$scope.addItem = function () {
		$scope.modelData = {};
		$scope.modelData.invitees = [];
	}

	$scope.load();

	$scope.validate = function () {
		return $scope.modelForm.$valid;
	}

	$scope.saveOrUpdate = function () {

		if (!$scope.validate()) {
			alertWarning(Labels.Warning.fillForm);
			return;
		}

		if (!$scope.modelData.id) {
			CrudUtil.save($scope.entityName, $scope.modelData).then(
				function (response) {
					alertSuccess(Labels.Success.save);
					$scope.afterSaveOrUpdateSuccessful(true);
				});
		} else {
			CrudUtil.update($scope.entityName, $scope.modelData).then(
				function (response) {
					alertSuccess(Labels.Success.update);
					$scope.afterSaveOrUpdateSuccessful(false);
				});
		}

	}

	$scope.afterSaveOrUpdateSuccessful = function (isSave) {
		$scope.modelData = {};
		$('#' + $scope.entityName + 'View').modal('hide');
		$scope.tableModel.callService();
	}
});

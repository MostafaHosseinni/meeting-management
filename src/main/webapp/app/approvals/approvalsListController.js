app.controller("approvalsListController",
	function ($scope, Labels, UiUtil, CrudUtil, FileUploadUtil,
		$uiRouter) {

		$scope.preload = function () {

			$scope.Labels = Labels;

			$scope.id = $uiRouter.globals.params.id;

			$scope.modelData = {};

			$scope.modelData.meeting = {};





			$scope.titlePage = Labels.Generals.list + " "
				+ Labels.Approvals.main;

			$scope.entityName = "Approvals";

			$scope.tableModel = UiUtil.getDefaultTableModel($scope,
				$scope.entityName);

			$scope.meetingTypeModel = UiUtil.getDefaultLocalComboModel([
				UiUtil.getDefaultComboLocalData(
					Labels.MeetingType.MEETING,
					Labels.MeetingType.MEETING),
				UiUtil.getDefaultComboLocalData(
					Labels.MeetingType.APPOINMENT,
					Labels.MeetingType.APPOINMENT),]);

			$scope.meetingPositionModel = UiUtil
				.getDefaultLocalComboModel([
					UiUtil.getDefaultComboLocalData(
						Labels.MeetingPosition.HOST,
						Labels.MeetingPosition.HOST),
					UiUtil.getDefaultComboLocalData(
						Labels.MeetingPosition.GUEST,
						Labels.MeetingPosition.GUEST)]);

			$scope.meetingRoomModel = UiUtil.getDefualtAutoCompleteModel(
				"MeetingRoom", "roomName", "roomName");

			$scope.SecretaryModel = UiUtil
				.getDefualtAutoCompleteModel("Profile",
					"userName", "userName");

			$scope.bossModel = UiUtil
				.getDefualtAutoCompleteModel("Profile",
					"userName", "userName");

			$scope.tableModel = UiUtil.getDefaultClientTableModel(
				$scope, "Agenda");
			$scope.tableModel.columns = [UiUtil.getDefaultColumn(
				Labels.Agenda.ruls, "ruls"),
			UiUtil.getDefaultColumn(Labels.Agenda.approvals, "approvals"),];



			CrudUtil.getById('Meeting', $scope.id).then(function (response) {
				$scope.modelData.meeting = response;
				if (!$scope.modelData.absents)
					$scope.modelData.absents = [];
				$scope.modelData.agenda = [];
				$scope.presentModel = UiUtil.getDefualtExChangeModel("Profile", true);
				$scope.presentModel.displayName = 'userName';
				$scope.presentModel.allLabel = Labels.Approvals.presents;
				$scope.presentModel.selectedLabel = Labels.Approvals.absents;
				$scope.presentModel.listData = $scope.modelData.meeting.invitees;

				var agendaData = [];

				for (var i = 0; i < $scope.modelData.meeting.agenda.length; i++) {
					agendaData.push(UiUtil.getDefaultComboLocalData($scope.modelData.meeting.agenda[i], $scope.modelData.meeting.agenda[i]));
				}

				$scope.agendaModel = UiUtil.getDefaultLocalComboModel(agendaData);


			});

		}

		$scope.load = function () {
			$scope.preload();
			$scope.temp = {};	
		}

		$scope.load();

		$scope.addApproval = function () {
			debugger
			$scope.modelData.agenda.push($scope.temp);
			$scope.temp = {};
		}



		$scope.saveOrUpdate = function () {


			if ($scope.modelData.approvalFile) {

				FileUploadUtil.uploadFileWithThumbnail(
					$scope.modelData.approvalFile).then(function (file) {
						$scope.modelData.approvalFile = file;
						$scope.callServiceAfterChecking();
					})
			} else
				$scope.callServiceAfterChecking();
		}

		$scope.callServiceAfterChecking = function () {
			$scope.modelData.presents = $scope.presentModel.listData;
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
		}
	});

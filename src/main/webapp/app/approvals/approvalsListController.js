app.controller("approvalsListController",
	function ($scope, Labels, UiUtil, CrudUtil, FileUploadUtil,
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

			$scope.tableModel = UiUtil.getDefaultTableModel($scope,
				$scope.entityName);

			$scope.meetingTypeModel = UiUtil.getDefaultLocalComboModel([
				UiUtil.getDefaultComboLocalData(
					Labels.MeetingType.MEETING,
					Labels.MeetingType.MEETING),
				UiUtil.getDefaultComboLocalData(
					Labels.MeetingType.APPOINMENT,
					Labels.MeetingType.APPOINMENT),
			]);

			$scope.meetingPositionModel = UiUtil
				.getDefaultLocalComboModel([
					UiUtil.getDefaultComboLocalData(
						Labels.MeetingPosition.HOST,
						Labels.MeetingPosition.HOST),
					UiUtil.getDefaultComboLocalData(
						Labels.MeetingPosition.GUEST,
						Labels.MeetingPosition.GUEST)
				]);

			$scope.meetingRoomModel = UiUtil.getDefualtAutoCompleteModel(
				"MeetingRoom", "roomName", "roomName");

			$scope.secretaryModel = UiUtil
				.getDefualtAutoCompleteModel("Profile",
					"userName", "userName");

			$scope.bossModel = UiUtil
				.getDefualtAutoCompleteModel("Profile",
					"userName", "userName");

			$scope.tableModel = UiUtil.getDefaultClientTableModel(
				$scope, "Agenda");
			$scope.tableModel.pageSize = 5;
			$scope.tableModel.columns = [UiUtil.getDefaultColumn(
					Labels.Agenda.ruls, "ruls"),
				UiUtil.getDefaultColumn(Labels.Agenda.approvals, "approvals"),
			];

			$scope.tableModel.operations[0] = OperationUtil.getDefaultIconOperation('fa fa-trash', function (element, entityName) {
				$scope.modelData.approvalRul = $scope.modelData.approvalRul
					.filter(function (el) {
						return el != element;
					});
			});

			$scope.tableModel.nextPageLabel = Labels.Buttons.nextPage;
			$scope.tableModel.previousPageLabel = Labels.Buttons.backPage;

			CrudUtil.getById('Meeting', $scope.id).then(function (response) {
				$scope.modelData.meeting = response;
				if (!$scope.modelData.absents)
					$scope.modelData.absents = [];
				$scope.modelData.approvalRul = [];
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

			$scope.checkEmptyApproval = function () {
			return $scope.templateForm.$valid;
		}

			$scope.addOutOfApproval = function () {
				if (!$scope.checkEmptyApproval()) {
				alertWarning(Labels.Warning.fillForm);
				return;
			} else {
			
				if ($scope.modelData.outOfApproval == null)
					$scope.modelData.outOfApproval = [];
				$scope.modelData.outOfApproval.push($scope.template.newOutOfApproval);
				$scope.template.newOutOfApproval = null;

			}
		}

		}

		$scope.removeItem = function (item) {
			$scope.modelData.outOfApproval = $scope.modelData.outOfApproval
				.filter(function (el) {
					return el != item;
				});

		}

		$scope.load = function () {
			$scope.preload();
			$scope.temp = {};
			$scope.template = {};



		}

		$scope.load();

		$scope.checkEmpty = function () {
			return $scope.tempForm.$valid;
		}


		$scope.addApproval = function () {
			if (!$scope.checkEmpty()) {
				alertWarning(Labels.Warning.fillForm);
				return;
			} else {
				$scope.modelData.approvalRul.push($scope.temp);
				$scope.temp = {};
			}
		}

	

		$scope.addItem = function () {
			$scope.modelData = {};
		}



		$scope.saveOrUpdate = function () {


			if ($scope.modelData.approvalFile) {

				FileUploadUtil.uploadFile(
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
			$location.path('meetingCalendarList');

		}
	});
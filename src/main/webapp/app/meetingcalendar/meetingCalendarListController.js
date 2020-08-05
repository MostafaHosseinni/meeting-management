app
	.controller(
		"meetingCalendarListController",
		function ($scope, Labels, UiUtil, CrudUtil, $uiRouter, OperationUtil, $location, CurrentUser) {

			$scope.preload = function () {

				$scope.Labels = Labels;

				$scope.titlePage = Labels.Generals.list + " "
					+ Labels.MeetingCalendar.main;

				$scope.id = $uiRouter.globals.params.id;

				$scope.modelData = {};

				$scope.modelData.meetingRoom = {};


				$scope.meetingCss = ['jalase1', 'jalase2', 'jalase3', 'jalase4', 'jalase5', 'jalase6'];

				$scope.entityName = "Meeting";

				$scope.dayDiff = 0;

				$scope.weekday = ['دوشنبه', 'سه شنبه', 'چهارشنبه',
					'پنج شنبه', 'جمعه', 'شنبه', 'یک شنبه'];

				$scope.meetingStatusModel = UiUtil
					.getDefaultLocalComboModel([
						UiUtil.getDefaultComboLocalData(
							Labels.MeetingStatus.NEW,
							Labels.MeetingStatus.NEW),
						UiUtil.getDefaultComboLocalData(
							Labels.MeetingStatus.CONFIRMED,
							Labels.MeetingStatus.CONFIRMED),
						UiUtil.getDefaultComboLocalData(
							Labels.MeetingStatus.REJECT,
							Labels.MeetingStatus.REJECT),]);

				$scope.meetingTypeModel = UiUtil
					.getDefaultLocalComboModel([
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

				$scope.tableModel = UiUtil.getDefaultClientTableModel(
					$scope, "MeetingCalendar");
				$scope.tableModel.pageSize = 7;

				$scope.tableModel.nextPage = function () {
					$scope.dayDiff = $scope.dayDiff + 1;
					$scope.callServiceMinWorking();

				}
				$scope.tableModel.previousPage = function () {
					$scope.dayDiff = $scope.dayDiff - 1;
					$scope.callServiceMinWorking();

				}



				$scope.meetingRoomModel = UiUtil
					.getDefualtAutoCompleteModel("MeetingRoom",
						"roomName", "roomName");

				$scope.inviteesModel = UiUtil
					.getDefualtAutoCompleteModel("Profile",
						"userName", "userName");
				$scope.inviteesModel.serviceName = "getAllInvitees";


				$scope.secretaryModel = UiUtil
					.getDefualtAutoCompleteModel("Profile",
						"userName", "userName");

				$scope.bossModel = UiUtil
					.getDefualtAutoCompleteModel("Profile",
						"userName", "userName");


				$scope.tableModel.valueRenderer = function (element,
					column) {
					if ('workDay' == column) {
						return element.workDay + " - " + element.dayDate;
					}
					return "";

				}

				$scope.tableModel.columnCss = function (element, column) {
					var value = column.value;
					if ('workDay' == value)
						return "work";

					var columnValue = element[value];

					if (columnValue) {

						if (columnValue.isMeeting) {
							return columnValue.css;
						}
						if (columnValue.isHoliday) {
							return "holiday";
						}

					}
				}

				$scope.tableModel.columnClicked = function (element,
					column) {
					var value = column.value;
					if ('workDay' == value)
						return;

					var columnValue = element[value];
					if (columnValue.id == null) {
						alertWarning(Labels.Warning.meetingIsEmpty);
					} else {
						CrudUtil
							.getById('Meeting', columnValue.id)
							.then(
								function (response) {
									$scope.modelData = response;
									$('#MeetingView')
										.modal('show');
								})

					}

				}

				$scope.tableModel.showTooltip = function (element,
					column) {
					var value = column.value;
					if ('workDay' == value)
						return;

					var columnValue = element[value];
					if (columnValue.id != null) {
						return columnValue.title;
					}
					return "";

				}
				
				$scope.operations = [];
				$scope.operations.push(OperationUtil.getDefaultIconOperation(
					'fa fa-plus', function () {
						if ($scope.modelData.agenda == null)
							$scope.modelData.agenda = [];
						$scope.modelData.agenda.push($scope.newAgenda);
						$scope.newAgenda = null;

					}));


			}

			$scope.removeItem = function (item) {
				$scope.modelData.agenda = $scope.modelData.agenda
					.filter(function (el) {
						return el != item;
					});

			}

			$scope.callServiceMinWorking = function () {
				$scope.schedule = [];
				CrudUtil
					.customGetService('WorkingHour',
						'getMinWorkingHour', $scope.dayDiff)
					.then(
						function (response) {
							if (response == "") {
								alertWarning("لطفا ابتدا ساعت کاری را وارد کنید");
								$location.path('workingHourList');
							} else {
								$scope.minStartTime = response;

								CrudUtil
									.customGetService(
										'WorkingHour',
										'getMaxWorkingHour',
										$scope.dayDiff)
									.then(
										function (response) {
											$scope.maxEndTime = response;
											$scope
												.callServiceAfterChecking();
											$scope
												.callWorkingHour();
										});
							}
						});
			}

			$scope.callWorkingHour = function () {
				CrudUtil
					.customGetService("WorkingHour", "findAllWithDate", $scope.dayDiff)
					.then(
						function (response) {

							for (i = 0; i < response.length; i++) {
								var day = {};
								day.workDay = response[i].workDay;
								day.dayDate = response[i].dayDate;
								for (j = $scope.minStartTime; j <= $scope.maxEndTime; j++) {
									var cell = {};
									cell.title = {};
									cell.isHoliday = false;
									cell.isMeeting = false;
									cell.id = null;

									day["work" + j] = cell;
									if (day.workDay == "جمعه") {
										day["work" + j].isHoliday = true;
									}
								}
								$scope.schedule.push(day);
							}
							$scope.callServiceHoliday();

						});
			}

			$scope.callServiceHoliday = function () {

				CrudUtil
					.customGetService('Holiday', 'getAllHoliday',
						$scope.dayDiff)
					.then(
						function (response) {

							for (i = 0; i < response.length; i++) {
								var holiday = response[i].holidayDate;
								var dayOfWeek = $scope.weekday[holiday];

								for (j = 0; j < $scope.schedule.length; j++) {
									if ($scope.schedule[j].workDay == dayOfWeek) {
										var day = $scope.schedule[j];
										for (k = $scope.minStartTime; k <= $scope.maxEndTime; k++) {

											day["work" + k].isHoliday = true;
										}
										break;
									}
								}

							}

							$scope.callServiceMeeting();
						});
			}

			$scope.callServiceMeeting = function () {

				CrudUtil
					.customGetService('Meeting', 'getAllMeeting',
						$scope.dayDiff)
					.then(
						function (response) {

							for (i = 0; i < response.length; i++) {
								var meeting = response[i].meetingDate;
								var meetingDay = $scope.weekday[meeting];

								for (j = 0; j < $scope.schedule.length; j++) {
									if ($scope.schedule[j].workDay == meetingDay) {

										var startMeeting = response[i].startTime;
										var endMeeting = response[i].endTime;

										var day = $scope.schedule[j];

										var rand = Math.floor(Math.random() * $scope.meetingCss.length);
										var css = $scope.meetingCss[rand];
										for (k = startMeeting; k < endMeeting; k++) {
											day["work" + k].isMeeting = true;
											day["work" + k].id = response[i].id;
											day["work" + k].title = response[i].title;
											day["work" + k].css = css;
										}

									}
								}

							}

						});
			}

			$scope.load = function () {
				$scope.preload();
				$scope.callServiceMinWorking();

				$scope.CurrentUser = CurrentUser;



			}

			$scope.callServiceAfterChecking = function () {
				$scope.tableModel.columns = [];
				$scope.tableModel.columns = [UiUtil.getDefaultColumn(
					Labels.MeetingCalendar.day, "workDay")];
				for (var i = $scope.minStartTime; i <= $scope.maxEndTime; i++) {
					$scope.tableModel.columns.push(UiUtil
						.getDefaultColumn(i, "work" + i));
				}
			}

			$scope.addItem = function () {
				$scope.modelData = {};
				$scope.modelData.invitees = [];
				$scope.modelData.agenda = [];
			}

			$scope.load();

			$scope.validate = function () {
				return $scope.modelForm.$valid;
			}

			$scope.reject = function () {
				CrudUtil.customGetService('Meeting', 'rejectMeeting',
					$scope.modelData.id).then(function () {
						$('#MeetingView').modal('hide');
						alertSuccess(Labels.Success.reject);
						$scope.callServiceMinWorking();

					});
			}

			$scope.preCheck = function () {

				if (!$scope.validate()) {
					alertWarning(Labels.Warning.fillForm);
					return;
				}

				CrudUtil.customPostService('Meeting', $scope.modelData,
					'preCheck').then(function (response) {

						var r = confirm(response.titleEroor);
						if (r == true) {
							debugger
							$scope.saveOrUpdate();
						} else {
							$scope.afterSaveOrUpdateSuccessful();
						}

					});
			}

			$scope.saveOrUpdate = function () {
				if (!$scope.modelData.id) {
					CrudUtil
						.save($scope.entityName, $scope.modelData)
						.then(
							function (response) {
								alertSuccess(Labels.Success.save);
								$scope
									.afterSaveOrUpdateSuccessful(true);
							});
				} else {
					CrudUtil
						.update($scope.entityName, $scope.modelData)
						.then(
							function (response) {
								alertSuccess(Labels.Success.update);
								$scope
									.afterSaveOrUpdateSuccessful(false);
							});
				}

			}

			$scope.afterSaveOrUpdateSuccessful = function (isSave) {
				$scope.modelData = {};
				$('#' + $scope.entityName + 'View').modal('hide');
				$scope.callServiceMinWorking();

			}
			$scope.afterLoad = function () {
				$('#MeetingView').modal('hide');

			}
		});

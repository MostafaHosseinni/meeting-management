app.controller("workingHourListController",
		function($scope, Labels, UiUtil, CurrentUser, CrudUtil) {

			$scope.preload = function() {

				$scope.Labels = Labels;

				$scope.titlePage = Labels.Generals.list + " "
						+ Labels.WorkingHour.main;

				$scope.entityName = "WorkingHour";

				$scope.tableModel = UiUtil.getDefaultTableModel($scope,
						$scope.entityName);

				$scope.workDayModel = UiUtil.getDefaultLocalComboModel([
						UiUtil.getDefaultComboLocalData(
								Labels.WeekDays.SATURDAY,
								Labels.WeekDays.SATURDAY),
						UiUtil.getDefaultComboLocalData(Labels.WeekDays.SUNDAY,
								Labels.WeekDays.SUNDAY),
						UiUtil.getDefaultComboLocalData(Labels.WeekDays.MONDAY,
								Labels.WeekDays.MONDAY),
						UiUtil.getDefaultComboLocalData(
								Labels.WeekDays.TUESDAY,
								Labels.WeekDays.TUESDAY),
						UiUtil.getDefaultComboLocalData(
								Labels.WeekDays.WEDNESDAY,
								Labels.WeekDays.WEDNESDAY),
						UiUtil.getDefaultComboLocalData(
								Labels.WeekDays.THURSDAY,
								Labels.WeekDays.THURSDAY),
						UiUtil.getDefaultComboLocalData(Labels.WeekDays.FRIDAY,
								Labels.WeekDays.FRIDAY), ]);

				$scope.tableModel.columns = [
						UiUtil.getDefaultColumn(Labels.WorkingHour.workDay,
								"workDay"),
						UiUtil.getDefaultColumn(Labels.WorkingHour.startTime,
								"startTime"),
						UiUtil.getDefaultColumn(Labels.WorkingHour.endTime,
								"endTime") ];

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
				var txt1 = $scope.modelData.startTime;
				var txt2 = $scope.modelData.endTime;

				if (txt1 > txt2) {

					alertWarning(Labels.Warning.correctlyFild);

				} else {
					$scope.callServiceAfterChecking();
				}

			}
			$scope.callServiceAfterChecking = function() {

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

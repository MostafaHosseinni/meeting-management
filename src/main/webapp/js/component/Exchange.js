'use strict';
app.directive('exchange', [
		'CrudUtil',
		'BaseTableUtil',

		function(CrudUtil, BaseTableUtil) {
			var directive = {};
			directive.restrict = 'E';
			directive.templateUrl = "/js/component/ExchangeTemplate.html";
			directive.scope = {
				"selectedModel" : "=model",
				"suggestModel" : "=suggestmodel",
			}

			directive.link = function($scope, elem, attr, ctrl) {
			
				$scope.preparedData = function(response) {
					$scope.suggestModel.listData = response;
					angular.forEach($scope.selectedModel, function(item) {
						var idx = $scope.suggestModel.listData.filter(function(
								obj) {
							return (obj.id != item.id)
						});
						$scope.suggestModel.listData = idx;
					});
				}
			
				if (!$scope.suggestModel.isLocal) {
					if ($scope.suggestModel.serviceName != undefined) {
						CrudUtil.customGetService(
								$scope.suggestModel.entityName,
								$scope.suggestModel.serviceName,
								$scope.suggestModel.serviceParam).then(
								function(response) {
									$scope.preparedData(response);
								});
					} else {
						BaseTableUtil
								.getBaseTable($scope.suggestModel.entityName)
								.then(function(response) {
									$scope.preparedData(response);
								});
					}
				}else{
					$scope.preparedData($scope.suggestModel.listData);
				}

				$scope.moveItem = function(items, from, to) {
					angular.forEach(items, function(item) {
						var idx = from.indexOf(item);
						from.splice(idx, 1);
						to.push(item);
					});
				};

				$scope.moveAll = function(from, to) {
					angular.forEach(from, function(item) {

						to.push(item);
					});
					from.length = 0;
				};

			}

			return directive;
		} ]);
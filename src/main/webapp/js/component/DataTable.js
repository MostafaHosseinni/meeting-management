
'use strict';
app
        .directive(
                'datatable',
                [
                    "$http",
                    "CrudUtil",
                    "UiUtil",
                    "Labels",
                    "OperationUtil",
                    function($http, CrudUtil, UiUtil, Labels, OperationUtil) {
                      var directive = {};
                      directive.restrict = 'E';
                      directive.scope = {
                        "tableModel": "=tablemodel"
                      };

                      directive.templateUrl = "js/component/DataTableTemplate.html";

                      directive.link = function($scope, elem, attr, ctrl) {

                        $scope.dataPopoverInput = "dataPopoverInput";

                        $scope.Labels = Labels;

                        $scope.tableModel.valueRenderer = function(element,
                                column) {

                          if (column.indexOf(".") == -1)
                            return element[column];

                          else {

                            var split = column.split(".");

                            for (var int = 0; int < split.length; int++) {
                              if (!element) break;
                              element = element[split[int]];
                            }

                            return element;
                          }

                        };

                        $scope.tableModel.ranges = UiUtil
                                .getDefaultLocalComboModel([
                                    UiUtil.getDefaultComboLocalData("5", 5),
                                    UiUtil.getDefaultComboLocalData("10", 10),
                                    UiUtil.getDefaultComboLocalData("20", 20),
                                    UiUtil.getDefaultComboLocalData("50", 50)]);

                        $scope.tableModel.ranges.change = function(size) {
                          if ($scope.tableModel.count != undefined
                                  && $scope.tableModel.count > 0) {
                            if (size) {
                              $scope.tableModel.paginationObject.size = size;
                              $scope.tableModel.paginationObject.lastPage = $scope.tableModel.count
                                      / $scope.tableModel.paginationObject.size;
                              $scope.tableModel.paginationObject.lastPage = Math
                                      .ceil($scope.tableModel.paginationObject.lastPage);
                              $scope.tableModel.paginationObject.page = 0;
                              $scope.tableModel.selectedPage = $scope.tableModel.paginationObject.page + 1;
                              $scope.tableModel.callDataService();
                            }
                          }
                        }

                        $scope.tableModel.selectPageOperation = OperationUtil
                                .getDefaultIconOperation(
                                        'fa fa-bolt',
                                        function() {
                                          $scope.tableModel
                                                  .selectPage(parseInt($scope.tableModel.selectedPage) - 1);
                                        });
                        $scope.tableModel.selectPageOperations = [$scope.tableModel.selectPageOperation];

                        $scope.tableModel.clearFilter = function() {
                          $scope.tableModel.tempExample = {};
                        }

                        $scope.tableModel.callService = function() {
                          if ($scope.tableModel
                                  .isValidToFilter($scope.tableModel.tempExample)) {
                            if ($scope.tableModel.hasFilter) {
                              var myObj = $scope.tableModel.tempExample;
                              for (var i = 0; i < $scope.tableModel.columns.length; i++) {
                                if ($scope.tableModel.columns[i].value
                                        .indexOf(".") != -1) {

                                  var split = $scope.tableModel.columns[i].value
                                          .split(".");
                                  var myObj1 = {};
                                  for (var j = split.length - 1; j >= 0; j--) {
                                    if (j == split.length - 1)
                                      myObj1[split[j]] = $scope.tableModel.tempExample[$scope.tableModel.columns[i].value];
                                    else if (j != 0)
                                      myObj1[split[j]] = myObj1;
                                    else
                                      myObj[split[j]] = myObj1;
                                    myObj[$scope.tableModel.columns[i].value] = $scope.tableModel.tempExample[$scope.tableModel.columns[i].value];
                                  }
                                } else {
                                  myObj[$scope.tableModel.columns[i].value] = $scope.tableModel.tempExample[$scope.tableModel.columns[i].value];
                                }
                              }

                              $scope.tableModel.tempExample = myObj;
                            }
                            $scope.tableModel.example = $scope.tableModel
                                    .preparedForCallService($scope.tableModel.tempExample);
                            $scope.tableModel.callDataService();
                          }

                        };

                        $scope.tableModel.callDataService = function() {
                          CrudUtil
                                  .getByServicePage(
                                          $scope.tableModel.entityName,
                                          $scope.tableModel.dataService,
                                         $scope.tableModel.paginationObject)
                                  .then(
                                          function(reponseData) {
                                        	  
                                        	  var data = reponseData.totalItems;
                                            if (data == undefined) {
                                              $scope.tableModel.count = 0;
                                              $scope.tableModel.paginationObject.lastPage = $scope.tableModel.count
                                                      / $scope.tableModel.paginationObject.size;
                                              $scope.tableModel.paginationObject.lastPage = Math
                                                      .ceil($scope.tableModel.paginationObject.lastPage);
                                              $scope.tableModel.paginationObject.page = 0;
                                              $scope.tableModel.selectedPage = $scope.tableModel.paginationObject.page;
                                              $scope.tableModel.elements = [];

                                            } else if (data == 0) {
                                              $scope.tableModel.count = data;
                                              $scope.tableModel.paginationObject.lastPage = $scope.tableModel.count
                                                      / $scope.tableModel.paginationObject.size;
                                              $scope.tableModel.paginationObject.lastPage = Math
                                                      .ceil($scope.tableModel.paginationObject.lastPage);
                                              $scope.tableModel.paginationObject.page = 0;
                                              $scope.tableModel.selectedPage = $scope.tableModel.paginationObject.page;
                                              $scope.tableModel.elements = [];

                                              alertWarning(
                                                      "نتیجه ای برای جستجو یافت نشد",
                                                      3000);

                                            }

                                            else {
                                              $scope.tableModel.count = data;

                                              $scope.tableModel.paginationObject.lastPage = $scope.tableModel.count
                                                      / $scope.tableModel.paginationObject.size;
                                              $scope.tableModel.paginationObject.lastPage = Math
                                                      .ceil($scope.tableModel.paginationObject.lastPage);
// $scope.tableModel.paginationObject.page = 0;
                                              $scope.tableModel.selectedPage = $scope.tableModel.paginationObject.page + 1;
                                              
                                              $scope.tableModel.elements = reponseData.elements;                                           
                                              }

                                          });

                        }

// $scope.tableModel.callDataService = function() {
// CrudUtil.customPostService(
// $scope.tableModel.entityName,
// $scope.tableModel,
// $scope.tableModel.dataService).then(
// function(reponseData) {
// $scope.tableModel.elements = reponseData;
// });
//
// }

                        $scope.tableModel.next = function() {
                          if ($scope.tableModel.paginationObject.page < $scope.tableModel.paginationObject.lastPage) {
                            $scope.tableModel.paginationObject.page++;
                            $scope.tableModel.selectedPage = $scope.tableModel.paginationObject.page + 1;
                            $scope.tableModel.callDataService();
                          }
                        }
                        $scope.tableModel.previous = function() {
                          if ($scope.tableModel.paginationObject.page >= 1) {
                            $scope.tableModel.paginationObject.page--;
                            $scope.tableModel.selectedPage = $scope.tableModel.paginationObject.page + 1;
                            $scope.tableModel.callDataService();
                          }
                        }
                        $scope.tableModel.selectPage = function(page) {
                          if (page < $scope.tableModel.paginationObject.lastPage) {
                            $scope.tableModel.paginationObject.page = page;
                            $scope.tableModel.selectedPage = $scope.tableModel.paginationObject.page + 1;
                            $scope.tableModel.callDataService();
                          }
                        }

                        if ($scope.tableModel.firstTimeLoad) {
                          $scope.tableModel.callService();
                        }

                      };
                      return directive;
                    }]);
'use strict';
app
	.factory(
		'UiUtil',
		[

			'$location',
			'OperationUtil',
			'CallService',
			function ($location, OperationUtil, CallService) {

				var model = {};
				return {
					pageName: '',
					getDefaultProjection: function (field) {
						var opt = {};
						opt.query = field;
						opt.field = field;
						return opt;
					},

					getDefaultComboLocalData: function (name, value) {
						var opt = {};
						opt.name = name;
						opt.value = value;
						return opt;
					},

					getDefaultLocalComboModel: function (localData) {
						var comboModel = {};
						comboModel.isLocal = true;
						comboModel.localData = localData;
						comboModel.displayName = "name";
						comboModel.showNoVal = true;
						comboModel.value = "value";
						comboModel.change = function () {

						}
						return comboModel;
					},

					getDefaultServiceComboModel: function (
						entityName) {
						return this.getDefaultServiceComboModel(
							entityName, 'name');
					},
					getDefaultServiceComboModel: function (
						entityName, displayName) {
						var comboModel = {};
						comboModel.entityName = entityName;
						comboModel.isLocal = false;
						comboModel.service = undefined;
						comboModel.displayName = displayName;
						comboModel.showNoVal = true;
						comboModel.localData = [];
						comboModel.change = function () {

						}
						return comboModel;
					},

					getDefaultSort: function () {
						var sortDTO = {};
						sortDTO.propertyName = 'id';
						sortDTO.ascending = false;
						return sortDTO;
					},

					getDefaultPagination: function () {
						var paginationObject = {};
						paginationObject.size = 10;
						paginationObject.page = 0;
						paginationObject.sort = "";
						return paginationObject;
					},

					getDefaultCheckBoxType: function (action,
						hasInputer) {
						var checkBoxType = {};
						checkBoxType.type = hasInputer;
						checkBoxType.action = action;
						return checkBoxType;
					},

					getDefaultTableModel: function (scope,
						entityName) {
						var tableModel = {};

						tableModel.entityName = entityName;

						tableModel.preparedForCallService = function (
							tempExample) {
							return tempExample;
						}

						tableModel.paginationObject = this
							.getDefaultPagination();

						tableModel.example = {};
						tableModel.elements = [];
						tableModel.tempExample = {};

						tableModel.indexCss = function (element) {
							return;
						};

						tableModel.selectedObject = [];

						tableModel.hasFilter = false;

						tableModel.hasCheckBox = false;
						tableModel.checkBoxType = undefined;

						tableModel.isValidToFilter = function (
							example) {
							return true;
						}

						tableModel.firstTimeLoad = true;

						tableModel.operations = [OperationUtil
							.getDefaultViewOperation(scope,
								entityName)
						];

						tableModel.dataService = 'pageable';

						return tableModel;

					},

					getDefaultClientTableModel: function (scope,
						entityName) {

						var tableModel = {};

						tableModel.entityName = entityName;

						tableModel.hasPaging = true;
						
						tableModel.nextPageLabel = '';
						tableModel.previousPageLabel = '';

						tableModel.pageSize = 50;
						tableModel.pageNumber = 0;

						tableModel.valueRenderer = function (element, column) {
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
						}


						tableModel.indexCss = function (element) {
							return;
						};

						tableModel.columnCss = function (element, column) {
							return;
						}

						tableModel.selectedObject = [];

						tableModel.hasCheckBox = false;

						tableModel.operations = [OperationUtil
							.getDefaultViewOperation(scope,
								entityName)
						];

						tableModel.nextPage = function () {
							tableModel.pageNumber = tableModel.pageNumber + 1;

						}
						tableModel.previousPage = function () {
							tableModel.pageNumber = tableModel.pageNumber - 1;
						}
						return tableModel;
					},

					getDefaultFilter: function () {
						var filter = {};

						filter.validation = ''; // any, text,
						// number, date,
						// etc...

						filter.filterable = true;

						filter.type = 'filtertext'; // text, date,
						// combo

						filter.values = '';

						filter.operations = [];

						return filter;
					},

					getFilter: function (filtertype, operations) {
						var filter = {};

						filter.validation = ''; // any, text,
						// number, date,
						// etc...

						filter.filterable = true;

						filter.type = filtertype;

						filter.values = '';

						filter.operations = operations;

						return filter;
					},

					getDefaultColumn: function (displayName, value) {

						var column = {};

						column.displayName = displayName;
						column.value = value;

						column.filter = this.getDefaultFilter();

						column.sortable = true;

						column.css = "colEqSmall";

						return column;

					},

					getColumnWithFilter: function (displayName,
						value, filtertype, operations) {

						var column = {};

						column.displayName = displayName;
						column.value = value;

						column.filter = this.getFilter(filtertype,
							operations);

						column.sortable = true;

						column.css = "colEqSmall";

						return column;

					},

					getDefaultColumn: function (displayName, value,
						css) {

						var column = {};

						column.displayName = displayName;
						column.value = value;

						column.filter = this.getDefaultFilter();

						column.sortable = true;

						column.css = css;

						return column;

					},

					getDefaultDateFilter: function () {
						var filter = {};

						filter.validation = ''; // any, text,
						// number, date,
						// etc...

						filter.filterable = true;

						filter.type = 'date'; // text, date, combo

						filter.values = '';

						return filter;
					},

					getDefaultDateColumn: function (displayName,
						value) {

						var column = {};

						column.displayName = displayName;
						column.value = value;

						column.css = 'colEqSmall';
						column.filter = this.getDefaultDateFilter();

						column.sortable = true;

						return column;

					},

					getDefualtAutoCompleteModel: function (
						entityName) {
						return this.getDefualtAutoCompleteModel(
							entityName, 'name', 'name');
					},

					getDefualtAutoCompleteModel: function (
						entityName, titleField, searchField) {
						var defaultObject = {};

						defaultObject.id = entityName + '_id';
						defaultObject.placeholder = 'جستجو';
						defaultObject.selectedObject = {};
						defaultObject.url = {};
						defaultObject.dataField = {};
						defaultObject.titleField = titleField;
						defaultObject.descriptionField = {};
						defaultObject.imageField = null;
						defaultObject.imageUri = null;
						defaultObject.inputClass = 'form-control from-control-small';
						defaultObject.userPause = 100;
						defaultObject.localData = {};
						defaultObject.searchFields = searchField;
						defaultObject.minLengthUser = 1;
						defaultObject.matchClass = 'highlight';
						defaultObject.serviceName = undefined;
						defaultObject.entityName = entityName;
						defaultObject.readOnly = false;

						return defaultObject;
					},

					getDefualtExChangeModel: function (entityName) {
						return getDefualtExChangeModel(entityName, false);
					},
					getDefualtExChangeModel: function (entityName, isLocal) {
						var defaultObject = {};

						defaultObject.serviceName = undefined;
						defaultObject.serviceParam = '';
						defaultObject.entityName = entityName;
						defaultObject.readOnly = false;
						defaultObject.isLocal = isLocal;
						defaultObject.displayName = 'name';
						defaultObject.allLabel = '';
						defaultObject.selectedLabel = '';
						defaultObject.listData = [];

						return defaultObject;
					},

					getDefualtRadioButtonModel: function (name,
						value) {
						var opt = {};
						opt.name = name;
						opt.value = value;
						return opt;
					},

					stringToBytes: function (str) {
						var bytes = [];
						for (var i = 0, n = str.length; i < n; i++) {
							var char = str.charCodeAt(i);
							bytes.push(char >>> 8, char & 0xFF);
						}
						return bytes;
					},

					byteToString: function (bytes) {
						var chars = [];
						for (var i = 0, n = bytes.length; i < n;) {
							chars.push(((bytes[i++] & 0xff) << 8) | (bytes[i++] & 0xff));
						}
						return String.fromCharCode.apply(null, chars);
					},

					getDefaultSlider: function (type, title, options, items) {
						var slider = {};
						slider.title = title;
						slider.type = type;
						slider.options = options;
						slider.items = items;
						return slider;
					},

					getDefaultSingleSlider: function (title, options, items) {
						return this.getDefaultSlider('single', title, options, items);
					},
					getDefaultBigSlider: function (items) {
						return this.getDefaultSlider('big', null, this.getDefaultOptionBigSlider(), items);
					},
					getDefaultMultipleSlider: function (title, options, items) {
						return this.getDefaultSlider('multiple', title, options, items);
					},
					getDefaultBlogSlider: function (title, options, items) {
						return this.getDefaultSlider('blog', title, options, items);
					},

					getDefaultSingleSliderItem: function (image, image_hover, title, description, link) {
						var item = {};
						item.image = image;
						item.image_hover = image_hover;
						item.title = title;
						item.description = description;
						item.link = link;

						return item;
					},
					getDefaultBigSliderItem: function (image, title, description, link) {
						var item = {};
						item.image = image;
						item.title = title;
						item.description = description;
						item.link = link;
						return item;
					},
					getDefaultBlogSliderItem: function (image, title, description, link) {
						var item = {};
						item.image = image;
						item.title = title;
						item.description = description;
						item.link = link;
						return item;
					},
					getDefaultOptionSlider: function (itemCount, navigation, navigationText) {
						var option = {};
						option.items = itemCount;
						option.navigation = navigation;
						option.navigationText = navigationText;
						return option;
					},
					getDefaultOptionBigSlider: function () {
						var option = {};
						option.autoPlay = 4000;
						option.stopOnHover = true;
						option.slideSpeed = 300;
						option.paginationSpeed = 600;
						option.items = 1;
						option.navigation = true;
						option.navigationText = ["<i class='icon fa fa-angle-right'></i>",
							"<i class='icon fa fa-angle-left'></i>"
						];
						option.dotsEach = true;

						return option;
					},
					getDefaultNavigationOptionSlider: function (itemCount) {
						return this.getDefaultOptionSlider(itemCount, true, ["", ""]);
					}

				}
			}
		]);

app.factory('OperationUtil', [

	'$location',
	'CrudUtil',
	function ($location, CrudUtil) {

		return {

			getDefaultViewOperation: function (scope, entityName) {

				var viewOperation = {};

				viewOperation.icon = 'fa fa-search';
				viewOperation.modalId = entityName + "View";

				viewOperation.action = function (element, entityName) {
					window.scroll(0, 0);
					CrudUtil.getById(entityName, element.id).then(
						function (modelData) {
							scope.modelData = modelData;
							return modelData;
						});

				}
				return viewOperation;
			},

			getDefaultPrintOperation: function () {

				var printOperation = {};

				printOperation.icon = 'zmdi zmdi-local-printshop';
				printOperation.title = '';
				printOperation.action = function (element, entityName) {
					localStorage.setItem("searchParvandePrintData", JSON
						.stringify(element));
					// window.open('searchParvandePrint.html', '_blank');
				}
				return printOperation;

			},

			getDefaultDeleteOperation: function () {

				var viewOperation = {};

				viewOperation.icon = 'fa fa-trash fa-lg';

				viewOperation.action = function (element, entityName) {
					localStorage.setItem('id', element.id);
					var path = entityName + "Edit";
					$location.path(path);
				}
				return viewOperation;
			},

			getDefaultRefreshOperation: function () {

				var viewOperation = {};

				viewOperation.icon = 'fa fa-refresh fa-lg';

				viewOperation.action = function (element, entityName) {
					localStorage.setItem('id', element.id);

				}
				return viewOperation;
			},

			getDefaultDownloadOperation: function (action) {
				var operation = {};
				operation.icon = 'fa fa-download';
				operation.title = '';
				operation.action = action;
				return operation;
			},

			getDefaultOperation: function (icon, modalId, title, action) {
				var operation = {};
				operation.icon = icon;
				operation.modalId = modalId;
				operation.title = title;
				operation.action = action;
				return operation;
			},
			getDefaultTitleOperation: function (title, action) {
				var operation = {};
				operation.icon = '';
				operation.title = title;
				operation.action = action;
				return operation;
			},
			getDefaultIconOperation: function (icon, action) {
				var operation = {};
				operation.icon = icon;
				operation.title = '';
				operation.action = action;
				return operation;
			},

		}
	}
]);

app.factory('Loader', [function () {
	return {
		loader: false,
		showLoader: function () {
			this.loader = true;
		},

		hideLoader: function () {
			this.loader = false;
		}
	}
}]);
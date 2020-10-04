var app = angular.module('myApp', [ 'ui.mask', 'ui.router', 'ngAnimate',
		'ui.bootstrap', 'ADM-dateTimePicker', 'bootstrap.fileField',
		'chart.js', 'ngSanitize', 'angucomplete', 'ng-file-model',
		'ui.carousel', 'angular-intro' ]);
app.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {

			$stateProvider.state('homeController', {
				url : '/',
				templateUrl : 'app/meetingcalendar/meetingCalendarList.html',
				controller : 'meetingCalendarListController'
			});

			$stateProvider.state('approvalsController', {
				url : '/approvalsList/:id',
				templateUrl : 'app/approvals/approvalsList.html',
				controller : 'approvalsListController'
			});
			$stateProvider.state('holidayController', {
				url : '/holidayList',
				templateUrl : 'app/holiday/holidayList.html',
				controller : 'holidayListController'
			});
			$stateProvider.state('meetingController', {
				url : '/meetingList',
				templateUrl : 'app/meeting/meetingList.html',
				controller : 'meetingListController'
			});
			$stateProvider.state('meetingRoomController', {
				url : '/meetingRoomList',
				templateUrl : 'app/meetingroom/meetingRoomList.html',
				controller : 'meetingRoomListController'
			});
			$stateProvider.state('serviceTypeController', {
				url : '/serviceTypeList',
				templateUrl : 'app/servicetype/serviceTypeList.html',
				controller : 'serviceTypeListController'
			});
			$stateProvider.state('smsController', {
				url : '/smsList',
				templateUrl : 'app/sms/smsList.html',
				controller : 'smsListController'
			});
			$stateProvider.state('smsConfigController', {
				url : '/smsConfigList',
				templateUrl : 'app/smsconfig/smsConfigList.html',
				controller : 'smsConfigListController'
			});

			$stateProvider.state('workingHourController', {
				url : '/workingHourList',
				templateUrl : 'app/workinghour/workingHourList.html',
				controller : 'workingHourListController'
			});

			$stateProvider.state('meetingCalendarListController', {
				url : '/meetingCalendarList/:id',
				templateUrl : 'app/meetingcalendar/meetingCalendarList.html',
				controller : 'meetingCalendarListController'
			});

			$stateProvider.state('changePasswordController', {
				url : '/changePassword',
				templateUrl : 'app/changepassword/changePassword.html',
				controller : 'changePasswordController'
			});
			$stateProvider.state('profileListController', {
				url : '/profileList',
				templateUrl : 'app/profile/profileList.html',
				controller : 'profileListController'
			});
			$stateProvider.state('approvalsViewController', {
				url : '/approvalsView',
				templateUrl : 'app/approvalsview/approvalsView.html',
				controller : 'approvalsViewController'
			});
			$stateProvider.state('approvalsDetailController', {
				url : '/approvalsDetail/:id',
				templateUrl : 'app/approvalsdetail/approvalsDetail.html',
				controller : 'approvalsDetailController'
			});

			$urlRouterProvider.otherwise('/');
		} ]);

app.directive('checkCharacter', function() {
	return function(scope, element, attrs) {
		element.bind("keydown keypress", function(event) {
			var patt1 = new RegExp("[\u0600-\u06FF]");
			if (patt1.test(event.key) == true || event.key == " ") {
				event.preventDefault();
			}

		});
	};
});

app.directive('ngEnter', function() {
	return function(scope, element, attrs) {
		element.bind("keydown keypress", function(event) {
			if (event.which === 13) {
				scope.$apply(function() {
					scope.$eval(attrs.ngEnter);
				});
				event.preventDefault();
			}
		});
	};
})

app.directive('contenteditable', [ '$sce', function($sce) {
	return {
		restrict : 'A', // only activate on element attribute
		require : '?ngModel', // get a hold of NgModelController
		link : function(scope, element, attrs, ngModel) {
			if (!ngModel)
				return; // do nothing if no ng-model

			// Specify how UI should be updated
			ngModel.$render = function() {
				element.html($sce.getTrustedHtml(ngModel.$viewValue || ''));
			};

			// Listen for change events to enable binding
			element.on('blur keyup change', function() {
				scope.$evalAsync(read);
			});
			read(); // initialize

			// Write data to the model
			function read() {
				var html = element.html();
				// When we clear the content editable the browser leaves a <br>
				// behind
				// If strip-br attribute is provided then we strip this out
				if (attrs.stripBr && html == '<br>') {
					html = '';
				}
				ngModel.$setViewValue(html);
			}
		}
	};
} ]);

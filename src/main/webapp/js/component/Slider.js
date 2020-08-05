'use strict';
app.directive('slider', [function() {
  var directive = {};
  directive.restrict = 'E';
  directive.scope = {
    "slider": "=slider",
  };

  directive.templateUrl = "/js/component/SliderTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
  }
  return directive;
}]);
'use strict';
app.directive('blogsliderproduct', [function() {
  var directive = {};
  directive.restrict = 'E';
  directive.scope = {
    "item": "=item",
  };

  directive.templateUrl = "/js/component/BlogSliderProductTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
    
  }
  return directive;
}]);
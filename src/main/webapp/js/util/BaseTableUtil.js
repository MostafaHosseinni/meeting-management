'use strict';
app.factory('BaseTableUtil', [

'CrudUtil', function(CrudUtil) {


	return {
		getBaseTable : function(entityName) {
			return CrudUtil.getCachableService(entityName, null, null);
		},
	}

} ]);
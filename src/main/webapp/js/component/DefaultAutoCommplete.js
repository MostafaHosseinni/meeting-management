function getDefualtObjectAuto(entityName) {
	var defaultObject = {};

	defaultObject.id = entityName+'_id';
	defaultObject.placeholder = 'جسنجو';
	defaultObject.selectedObject = {};
	defaultObject.url = {};
	defaultObject.dataField = {};
	defaultObject.titleField = 'name';
	defaultObject.descriptionField = {};
	defaultObject.imageField = null;
	defaultObject.imageUri = null;
	defaultObject.inputClass = 'form-control from-control-small';
	defaultObject.userPause = 100;
	defaultObject.localData = {};
	defaultObject.searchFields = 'name';
	defaultObject.minLengthUser = 1;
	defaultObject.matchClass = 'highlight';
	defaultObject.serviceName = 'getAll';
	defaultObject.entityName = entityName;

	return defaultObject;
}
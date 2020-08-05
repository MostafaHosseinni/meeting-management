'use strict';
app.factory('CrudUtil', [
		'CallService',
		'$cacheFactory',
		function(CallService, $cacheFactory) {

			var prefixUrl = '/';
			return {

				getAllByPage : function(entityName, paginationDTO) {
					var url = prefixUrl + entityName + '/pageable';
					if(paginationDTO){
						url +="?" + serialize(paginationDTO);
					}
					return CallService.getByResponse(url).then(function(response) {
						var result = {};
						result.elements = response.data;
						result.totalItems = response.headers('X-Total-Count');
						return result;
					})
				},
				getByServicePage : function(entityName , service, paginationDTO) {
					var url = prefixUrl + entityName + '/' + service;
					if(paginationDTO){
						url +="?" + serialize(paginationDTO);
					}
					return CallService.getByResponse(url).then(function(response) {
						var result = {};
						result.elements = response.data;
						result.totalItems = response.headers('X-Total-Count');
						return result;
					})
				},
                postSearchByPage : function(entityName, data,paginationDTO , methodName) {
                    var url = prefixUrl + entityName +"/"+ methodName;
                    if(paginationDTO){
                        url +="?" + serialize(paginationDTO);
                    }
                    return CallService.postByResponse(url , data).then(function(response) {
                        var result = {};
                        result.elements = response.data;
                        result.totalItems = response.headers('X-Total-Count');
                        return result;
                    })
                },

				getAll : function(entityName) {
					return CallService.get(prefixUrl + entityName);
				},

				getById : function(entityName, id) {
					return CallService.get(prefixUrl + entityName + "/" + id);
				},

				save : function(entityName, dto) {
					return CallService.post(prefixUrl + entityName, dto);
				},

				update : function(entityName, dto) {
					return CallService.put(prefixUrl + entityName, dto);
				},

				deleteById : function(entityName, id) {
					return CallService
							.remove(prefixUrl + entityName + "/" + id);
				},

				customPostService : function(entityName, dto, methodName) {
					return CallService.post(prefixUrl + entityName + "/"
							+ methodName, dto);
				},
				customGetService : function(entityName, methodName, param) {
					return CallService.get(prefixUrl + entityName
							+ (methodName == null ? "" : ("/" + methodName))
							+ (param == null ? "" : ("/" + param)));
				},

				getCachableService : function(entityName, methodName, param) {
					return CallService.getCachable(prefixUrl + entityName
							+ (methodName == null ? "" : ("/" + methodName))
							+ (param == null ? "" : ("/" + param)));
				},

				removeCache : function(entityName) {
					return $cacheFactory.get('$http').remove(
							prefixUrl + entityName + "/getAll");
				}

			};

		} ]);


function serialize (obj) {
	  var str = [];
	  for (var p in obj)
	    if (obj.hasOwnProperty(p)) {
	      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	    }
	  return str.join("&");
	}

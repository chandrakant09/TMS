(function() {
    'use strict';

    var downloadModule = angular.module('components.donwload', []);

    downloadModule.factory('downloadService', ['$q', '$timeout', '$window',
        function($q, $timeout, $window) {
            return {
                download: function(fileId) {

                    var defer = $q.defer();

                    $timeout(function() {
                            $window.location = WSBASEURL+'filedownloadService/downloadFile?fileId=' + fileId;

                        }, 1000)
                        .then(function() {
                            defer.resolve('success');
                        }, function() {
                            defer.reject('error');
                        });
                    return defer.promise;
                }
            };
        }
    ]);
})();
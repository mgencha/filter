var app = angular.module('search', []);
app.controller('searchCtrl', function($scope, $http){
    $http.get('/api').success(function(response) {
        $scope.names = response.results;
    }).error(function(response) {
        console.log("Unable to retrieve the data...");
    });
});

app.filter('searchFor', function(){
    return function(arr, searchString){
    if(!searchString){
        return arr;
    }
    var result = [];
    searchString = searchString.toLowerCase();
    angular.forEach(arr, function(term){
        if(term.name.toLowerCase().indexOf(searchString) !== -1){
            result.push(term);
        }
    });
    return result;
    };
});
angular.module('nabApp.controllers', []).controller('CustomerListController', function($scope, $state, popupService, $window, Customer) {
  $scope.customers = Customer.query(); //fetch all customers

  $scope.deleteCustomer = function(customer) {
    if (popupService.showPopup('Really delete this customer record?')) {
      customer.$delete(function() {
        $window.location.href = ''; //redirect to home
      });
    }
  };
}).controller('CustomerViewController', function($scope, $stateParams, Customer) {
  $scope.customer = Customer.get({ id: $stateParams.id }); //GET customer
}).controller('CustomerCreateController', function($scope, $state, $stateParams, Customer) {
  $scope.customer = new Customer();  //new customer to ng-model

  $scope.addCustomer = function() {
    $scope.customer.$save(function() {
      $state.go('customers'); // on success go back to default - customers state
    });
  };
}).controller('CustomerEditController', function($scope, $state, $stateParams, Customer) {
  $scope.updateCustomer = function() { //PUT update customer
    $scope.customer.$update(function() {
      $state.go('customers'); // on success go back to default - customers state
    });
  };

  $scope.loadCustomer = function() { //GET customer to update
    $scope.customer = Customer.get({ id: $stateParams.id });
  };

  $scope.loadCustomer(); // load customer for edit
});
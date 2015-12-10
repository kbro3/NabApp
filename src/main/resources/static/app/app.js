angular.module('nabApp', ['ui.router', 'ngResource', 'nabApp.controllers', 'nabApp.services']);

angular.module('nabApp').config(function($stateProvider) {
  $stateProvider.state('customers', { // state for showing all customers
    url: '/customers',
    templateUrl: 'partials/customers.html',
    controller: 'CustomerListController'
  }).state('viewCustomer', { //state for showing single customer
    url: '/customers/:id/view',
    templateUrl: 'partials/customer-view.html',
    controller: 'CustomerViewController'
  }).state('newCustomer', { //state for adding a new customer
    url: '/customers/new',
    templateUrl: 'partials/customer-add.html',
    controller: 'CustomerCreateController'
  }).state('editCustomer', { //state for updating a customer
    url: '/customers/:id/edit',
    templateUrl: 'partials/customer-edit.html',
    controller: 'CustomerEditController'
  });
}).run(function($state) {
  $state.go('customers'); //make a transition to default customers state on app start
});
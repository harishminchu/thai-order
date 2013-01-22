'use strict';

var thaiApp = angular.module('thaiApp', [ 'ngResource' ]);

thaiApp.config([ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	$routeProvider.when('/order', {
		templateUrl : 'partials/orderlist.html',
		controller : OrderListCtrl
	}).when('/order/:orderId', {
		templateUrl : 'partials/order.html',
		controller : OrderCtrl
	}).when('/products', {
		templateUrl : 'partials/products.html',
		controller : ProductsCtrl
	}).otherwise({
		redirectTo : '/order'
	});
} ]);

thaiApp.directive('focusOn', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			scope.$watch(attrs.focusOn, function(value) {
				if (attrs.focusOn) {
					window.setTimeout(function() {
						element.focus();
					}, 10);
				}
			}, true);
		}
	};
});

thaiApp.factory('productService', [ '$resource', function($resource) {
	var Product = $resource('data/product/:productId', {
		productId : '@id'
	});
	var cachedProducts = null;
	return {
		getProducts : function() {
			if (cachedProducts == null)
				cachedProducts = Product.query();
			return cachedProducts;
		}
	};
} ]);

thaiApp.factory('orderService', [ '$resource', function($resource) {
	var Order = $resource('data/order/:orderId', {
		orderId : '@id'
	});
	return {
		add : function(callback) {
			Order.save({}, callback);
		},
		list : function() {
			return Order.query();
		},
		get : function(id) {
			return Order.get({
				orderId : id
			});
		}
	};
} ]);

/* Controllers */

function MainCtrl($scope, $location) {
	$scope.nav = [ {
		route : '/order',
		name : 'Orders'
	}, {
		route : '/products',
		name : 'Products'
	} ];

	$scope.isActive = function(entry) {
		return $location.path().search(entry.route) >= 0;
	};
}
MainCtrl.$inject = [ '$scope', '$location' ];

function ProductsCtrl($scope, productService) {
	$scope.products = productService.getProducts();
}
ProductsCtrl.$inject = [ '$scope', 'productService' ];

function OrderListCtrl($scope, orderService, $location) {
	$scope.orderList = orderService.list();
	$scope.add = function() {
		orderService.add(function(newOrder) {
			$location.url('/order/' + newOrder.id);
		});
	};
}
OrderListCtrl.$inject = [ '$scope', 'orderService', '$location' ];

function OrderCtrl($scope, $routeParams, productService, orderService) {

	$scope.refocus = true;

	$scope.products = productService.getProducts();

	$scope.order = orderService.get($routeParams.orderId);

	$scope.sum = function(onlyPaid) {
		var sum = 0, pos = $scope.order.items;
		if(pos) {
			for ( var i = 0; i < pos.length; i++) {
				if (!onlyPaid || pos[i].paid) {
					sum += pos[i].product.price;
				}
			}
		}
		return sum;
	};

	$scope.add = function() {
		if (!$scope.order.items) {
			$scope.order.items = [];
		}
		$scope.order.items.push({
			name : $scope.name,
			product : $scope.product,
			paid : $scope.paid
		});
		$scope.name = $scope.product = $scope.paid = null;
		$scope.order.$save();
		$scope.refocus = true;
	};

	$scope.remove = function(index) {
		$scope.order.items.splice(index, 1);
		$scope.order.$save();
	};

	$scope.paidChanged = function() {
		$scope.order.$save();
	};

	$scope.deleteOrder = function() {
		$scope.order.$delete();
	};

}
OrderCtrl.$inject = [ '$scope', '$routeParams', 'productService', 'orderService' ];

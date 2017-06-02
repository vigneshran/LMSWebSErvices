/**
 * 
 */

var lmsApp = angular.module("lmsApp", ["ngRoute", "simplePagination", "ui.bootstrap.modal", "ngNotify", "ngSanitize"]);

lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/", {
		redirectTo: "/home"
	}).when("/home",{
		templateUrl: "welcome.html"
	}).when("/admin",{
		templateUrl: "updateanddeletebooks.html"
	}).when("/librarian",{
		templateUrl: "librarian.html"
	}).when("/borrower",{
		templateUrl: "borrower.html"
	}).when("/adminauthor",{
		templateUrl: "adminupdateanddeleteauthors.html"
	}).when("/adminbook",{
		templateUrl: "updateanddeletebooks.html"
	}).when("/adminbranch",{
		templateUrl: "adminupdateanddeletebranches.html"
	}).when("/adminoverride",{
		templateUrl: "adminoverride.html"
	}).when("/adminpublisher",{
		templateUrl: "adminupdateanddeletepublishers.html"
	}).when("/adminborrower",{
		templateUrl: "adminupdateanddeleteborrowers.html"
	}).when("/librarian",{
		templateUrl: "librarianNew.html"
	}).when("/borrowerPage/:bid",{
		templateUrl: "borrowerPage.html"
	})
}])


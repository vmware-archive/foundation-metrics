var endpoint = 'http://<endpoint>';
var timeOutInterval = 60000;
var dataToLoad = 4;

var metricsModule = angular
		.module('metricsApp', [ "chart.js" ])
		.directive(
				'loading',
				function() {
					return {
						restrict : 'E',
						replace : true,
						template : '<div class="loading"><img src="./images/ajax-loader.gif" width="20" height="20" align="center"/>Please wait while the data is being pulled</div>',
						link : function(scope, element, attr) {
							scope.$watch('loading', function(val) {
								if (val)
									$(element).show();
								else
									$(element).hide();
							});
						}
					}
				})
		.directive(
				'collapseToggler',
				function() {
					return {
						restrict : 'A',
						link : function(scope, element, attr) {
							element
									.on(
											'click',
											function() {
												$(this).siblings('.collapse')
														.toggleClass('in');
												if ($(this)
														.find('.glyphicon')
														.hasClass(
																"glyphicon-chevron-down")) {
													$(this)
															.find('.glyphicon')
															.removeClass(
																	"glyphicon-chevron-down")
															.addClass(
																	"glyphicon-chevron-up");
												} else {
													$(this)
															.find('.glyphicon')
															.removeClass(
																	"glyphicon-chevron-up")
															.addClass(
																	"glyphicon-chevron-down");
												}
											});
						}
					};
				})
		.controller(
				'metricsCtrl',
				function($scope, $timeout, $http) {

					$scope.turnOffLoading = function() {
						if (dataToLoad === 0) {
							$scope.loading = false;
						}
					}

					$scope.populateCharts = function() {
						$scope.deploymentChartMap = [];
						uniqueDeploymentNames = [];

						var chartLabels = [];
						var chartData = [];
						_.each($scope.vmMetrics, function(vmMetric) {
							if (!_.contains(uniqueDeploymentNames,
									vmMetric.jobDetail.deployment)) {
								uniqueDeploymentNames
										.push(vmMetric.jobDetail.deployment);
							}
						});

						_
								.each(
										uniqueDeploymentNames,
										function(uniqueDeploymentName) {
											chartMeta = {};
											chartMeta["healthydata"] = [];
											chartMeta["healthylabels"] = [];
											chartMeta["unhealthydata"] = [];
											chartMeta["unhealthylabels"] = [];
											_
													.each(
															$scope.vmMetrics,
															function(vmMetric) {
																if (vmMetric.jobDetail.deployment == uniqueDeploymentName) {
																	if (vmMetric.fixedAttribute.system_healthy == "1.0") {
																		var jobName = vmMetric.jobDetail.job
																				+ " - "
																				+ vmMetric.fixedAttribute.system_disk_persistent_percent
																				+ "%";

																		chartMeta.healthylabels
																				.push(jobName);
																		if (vmMetric.fixedAttribute.system_cpu_user == "0.0") {
																			chartMeta.healthydata
																					.push("0.1");
																		} else {
																			chartMeta.healthydata
																					.push(vmMetric.fixedAttribute.system_cpu_user);
																		}
																	} else {
																		chartMeta.unhealthylabels
																				.push(vmMetric.jobDetail.job);
																		chartMeta.unhealthydata
																				.push(vmMetric.fixedAttribute.system_mem_percent);
																	}
																}
															});

											$scope.deploymentChartMap
													.push({
														deploymentname : uniqueDeploymentName,
														chartMeta : chartMeta
													});
										});

					}

					$scope.totals = function() {
						_
								.each(
										$scope.customJobMetrics,
										function(customJobMetric) {
											if (customJobMetric.jobDetail.job === "CloudController"
													&& customJobMetric.jobDetail.deployment === "untitled_dev") {
												$scope.total_user_count = _
														.find(
																customJobMetric.customAttributes,
																function(
																		attribute) {
																	if (attribute.name === "total_users") {
																		return attribute.value;
																	}
																})
											}

											if (customJobMetric.jobDetail.job === "Router"
													&& customJobMetric.jobDetail.deployment === "untitled_dev") {
												$scope.total_routes = _
														.find(
																customJobMetric.customAttributes,
																function(
																		attribute) {
																	if (attribute.name === "router.total_routes") {
																		return attribute.value;
																	}
																})
											}
										});
					}

					$scope.parseAppData = function() {
						var java = 0;
						var node = 0;
						var python = 0;
						var go = 0;
						var ruby = 0;
						var nullbuildpack = 0;
						var binary = 0;
						var staticfile = 0;
						var php = 0;
						var tcServer = 0;
						var other = 0;

						_
								.each(
										$scope.apps,
										function(app) {
											_
													.find(
															$scope.spaces,
															function(space) {
																if (app.spaceGUID === space.meta.guid) {
																	app.space_name = space.name;
																	app.org_name = space.organization.name;
																	if (isNaN(space.number_of_apps)) {
																		space.number_of_apps = 0;
																	}
																	if (isNaN(space.number_of_app_instances)) {
																		space.number_of_app_instances = 0;
																	}
																	space.number_of_apps += 1;
																	space.number_of_app_instances += app.instances;
																}
															})

											if (app.buildpack != null) {
												if (app.buildpack
														.indexOf('java') > -1
														|| app.buildpack
																.indexOf('Java') > -1) {
													java += 1;
												} else if (app.buildpack
														.indexOf('node') > -1
														|| app.buildpack
																.indexOf('Node') > -1) {
													node += 1;
												} else if (app.buildpack
														.indexOf('python') > -1
														|| app.buildpack
																.indexOf('Python') > -1) {
													python += 1;
												} else if (app.buildpack
														.indexOf('go') > -1
														|| app.buildpack
																.indexOf('Go') > -1) {
													go += 1;
												} else if (app.buildpack
														.indexOf('ruby') > -1
														|| app.buildpack
																.indexOf('Ruby') > -1) {
													ruby += 1;
												} else if (app.buildpack
														.indexOf('null') > -1
														|| app.buildpack
																.indexOf('Null') > -1) {
													nullbuildpack += 1;
												} else if (app.buildpack
														.indexOf('binary') > -1
														|| app.buildpack
																.indexOf('Binary') > -1) {
													binary += 1;
												} else if (app.buildpack
														.indexOf('static') > -1
														|| app.buildpack
																.indexOf('Static') > -1) {
													staticfile += 1;
												} else if (app.buildpack
														.indexOf('php') > -1
														|| app.buildpack
																.indexOf('Php') > -1) {
													php += 1;
												} else if (app.buildpack
														.indexOf('tc') > -1
														|| app.buildpack
																.indexOf('Tc') > -1) {
													tcServer += 1;
												} else {
													other += 1;
												}
											}
										});

						$scope.buildpacklabels = [ "java-buildpack",
								"nodejs-buildpack", "python-buildpack",
								"go-buildpack", "ruby-buildpack",
								"null-buildpack", "binary-buildpack",
								"staticfile-buildpack", "php-buildpack",
								"tcserver-buildpack", "other" ];
						$scope.buildpackdata = [ java, node, python, go, ruby,
								nullbuildpack, binary, staticfile, php,
								tcServer, other ];
					}

					var totals = function() {
						$http.get(endpoint + '/totals').success(function(data) {
							$scope.totallist = data;
							dataToLoad--;
							$scope.turnOffLoading();
							$timeout(totals, timeOutInterval);
						}).error(
								function(msg, response) {
									console.log(msg, response);
									$('.alert-danger').html(
											"Error while invoking the server")
											.show();
								});
					}

					var metrics = function() {
						$http
								.get(endpoint + '/metrics')
								.success(
										function(data) {
											$scope.vmMetrics = data.vmMetrics;
											$scope.customJobMetrics = data.customJobMetrics;
											$scope.totals();
											dataToLoad--;
											$scope.turnOffLoading();
											$scope.populateCharts();
											$timeout(metrics, timeOutInterval);
										})
								.error(
										function(response) {
											$scope.loading = false;
											$('.alert-danger')
													.html(
															"Error while invoking the server")
													.show();
											console.log(response);
										});
					}

					var orgs = function() {
						$http.get(endpoint + '/orgs').success(function(data) {
							$scope.orgs = data;
							dataToLoad--;
							$scope.turnOffLoading();
							$timeout(orgs, timeOutInterval);
						}).error(
								function(response) {
									console.log(response);
									$('.alert-danger').html(
											"Error while invoking the server")
											.show();
								});
					}

					var spaces = function() {
						$http.get(endpoint + '/spaces').success(function(data) {
							$scope.spaces = data;
							$scope.parseAppData();
							dataToLoad--;
							$scope.turnOffLoading();
							$timeout(spaces, timeOutInterval);
						}).error(
								function(response) {
									console.log(response);
									$('.alert-danger').html(
											"Error while invoking the server")
											.show();
								});
					}

					var apps = function() {
						$http.get(endpoint + '/apps').success(function(data) {
							$scope.apps = data;
							$scope.parseAppData();

							$timeout(apps, timeOutInterval);
						}).error(
								function(response) {
									console.log(response);
									$('.alert-danger').html(
											"Error while invoking the server")
											.show();
								});
					}

					var settings = function() {
						$http.get(endpoint + '/settings/mail').success(
								function(data) {
									$scope.mail = data;
									$timeout(settings, timeOutInterval);
								}).error(
								function(response) {
									console.log(response);
									$('.alert-danger').html(
											"Error while invoking the server")
											.show();
								});
					}
					
					$scope.getSettings = function() {
						settings();
					}

					$scope.saveEmail = function() {
						event.preventDefault();
						$http.post(endpoint + '/settings/mail', $scope.mail).success(
								function(data) {
									$scope.mail = data;
									$timeout(settings, timeOutInterval);
								}).error(
								function(response) {
									console.log(response);
									$('.alert-danger').html(
											"Error while invoking the server")
											.show();
								});
					}

					var execute = function() {
						$scope.loading = true;
						$('.alert-danger').hide();
						totals();
						metrics();
						orgs();
						spaces();
						apps();
						settings();
					}

					execute();

				});

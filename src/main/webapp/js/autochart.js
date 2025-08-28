'use strict';

q(function() {
	q("canvas").each(function(canvas) {
		q.ajax(canvas.attr("data-captor")).success(function(result) {
			new Chart(canvas.obj[0], {
				type: result.type,
				data: result.data,
				options: {
					indexAxis: result.type == 'bar' ? 'y': 'x',
					plugins: {
						title: {
							display: true,
							text: result.title
						}
					},
					elements: {
						point: {
							pointStyle: false
						}
					}
				}
			});
		}).process();
	});
});

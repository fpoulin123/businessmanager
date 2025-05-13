function createDoughnutChart(data, legend, title) {
	var xValues = legend;
	var yValues = data;
	const barColors = ["blue", "red"];


	var chart = new Chart("myChart", {
		type: "doughnut",
		data: {
			labels: xValues,
			datasets: [{
				backgroundColor: barColors,
				data: yValues
			}]
		},
		options: {
			responsive: true,
			maintainAspectRatio: false,
			title: {
				display: true,
				text: title
			}
		}
	});

}


function createBarChart(cnvId, title, legend, xData, yData) {

	var barchart1 = new Chart(cnvId, {
		type: 'bar',
		data: {
			labels: xData,
			datasets: [{
				label: legend,
				data: yData,
				borderWidth: 1
			}]
		},
		options: {
			title: {
				display: true,
				text: title
			},
			scales: {
				y: {
					min: 0,
					beginAtZero: false
				}
			}
		}
	});

}

function createBarChart2Series(cnvId, title, xData, legend1, yData1, legend2, yData2, legend3, yData3) {

	var barchart2 = new Chart(cnvId, {
		type: 'bar',
		data: {
			labels: xData,
			datasets: [{
				label: legend1,
				data: yData1,
				borderWidth: 1
			},
			{
				label: legend2,
				data: yData2,
				borderWidth: 1
			},
			{
				label: legend3,
				data: yData3,
				borderWidth: 1
			}]
		},
		options: {
			title: {
				display: true,
				text: title
			},

			scales: {
				x: {
					stacked: true
				},
				y: {
					min: 0,
					//beginAtZero: false,
					stacked: true
				}
			}
		}
	});

}
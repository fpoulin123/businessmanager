function createDoughnutChart(data, legend, title){
			var xValues = legend;
			var yValues = data;
			const barColors = ["blue","red"];
			
			
			new Chart("myChart", {
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
		
		
function createBarChart(cnvId, title, legend, xData, yData){
	
	new Chart(cnvId, {
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
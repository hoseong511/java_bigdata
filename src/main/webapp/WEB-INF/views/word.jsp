<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.hoho.java_bigdata.Wordcloud" %>




<!DOCTYPE html>
<html>
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="resources/d3.layout.cloud.js"></script>
<head>
    <title>Word Cloud Example</title>
</head>
<style>
    body {
        font-family:"Lucida Grande","Droid Sans",Arial,Helvetica,sans-serif;
    }
    .legend {
        border: 1px solid #555555;
        border-radius: 5px 5px 5px 5px;
        font-size: 0.8em;
        margin: 10px;
        padding: 8px;
    }
    .bld {
        font-weight: bold;
    }
</style>
<body>

</body>
<script>


    var frequency_list = [];
    
    
    <%	    
    	Map<String, Integer> result = Wordcloud.SongForWordCloud();
    	for(Map.Entry<String, Integer> elem: result.entrySet()){
   	%>
   			var word_count = {};
			word_count["text"] = "<%=elem.getKey()%>";
			word_count["size"] = "<%=elem.getValue()%>";
			frequency_list.push(word_count);
	<%
    	}
    %>
    console.log(frequency_list);
    
					   		


    var color = d3.scale.linear()
            .domain([0,1,2,3,4,5,6,10,15,20,100])
            .range(["#FF0000", "#FF8000", "#fff511", "#40FF00", "#0100f6", "#0d0189", "#7401DF", "#FA58F4", "#0e0404", "#919191", "#fff4e0", "#c7c7c7"]);

    d3.layout.cloud().size([1000, 1000])
            .words(frequency_list)
            .rotate(0)
            .fontSize(function(d) { return d.size; })
            .on("end", draw)
            .start();

    function draw(words) {
        d3.select("body").append("svg")
                .attr("width", 950)
                .attr("height", 950)
                .attr("class", "wordcloud")
                .append("g")
                // without the transform, words words would get cutoff to the left and top, they would
                // appear outside of the SVG area
                .attr("transform", "translate(320,200)")
                .selectAll("text")
                .data(words)
                .enter().append("text")
                .style("font-size", function(d) { return d.size + "px"; })
                .style("fill", function(d, i) { return color(i); })
                .attr("transform", function(d) {
                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                })
                .text(function(d) { return d.text; });
    }
</script>

<div style="width: 40%;">
    <div class="legend">
        Commonly used words are larger and slightly faded in color.  Less common words are smaller and darker.
    </div>

</div>


</html>
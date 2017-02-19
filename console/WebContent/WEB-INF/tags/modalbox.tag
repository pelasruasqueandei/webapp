<%@ attribute name="linktext" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="titlemessage" required="true" %>
<%@ attribute name="bodymessage" required="true" %>
<%@ attribute name="footeractiontext" required="true" %>
<%@ attribute name="footeractionhref" required="true" %>

<a href="#" data-toggle="modal" data-target="#myModal${id}">${linktext}</a>					
<div id="myModal${id}" class="modal fade" role="dialog">                 
   	<div class="modal-dialog">                                                                
   		<div class="modal-content">                                                           
   			<div class="modal-header">                                                        
   				<button type="button" class="close" data-dismiss="modal">&times;</button> 
   				<h4 class="modal-title">${titlemessage}</h4>                                     
   			</div>                                                                              
   			<div class="modal-body">                                                          
   				<p>${bodymessage}</p>                           
   			</div>                                                                              
   			<div class="modal-footer">                                                        
   				<a href="${footeractionhref}"><button class="btn btn-info">${footeractiontext}</button></a>
   			</div>                                                                              
   		</div>                                                                                  
   	</div>                                                                                      
</div>   
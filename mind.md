# question and mind

* question:

  while I'm trying to save an event to local room database, I want to save current time to room, then, when it need to show, I get the time from room and mapper it to a presenter object so I can show it on UI with right way I wanted.  

* mind: 

  But when I saved the time, I need the domain logic to get the current time and  mapper it to database time and save it, when I need it, I should get it and mapper it to ui object in domain case. Secondly, when I update an event, I need update the <big>*update_time*</big> without update <big>*create_time*</big>, so I should let domain case get the current time to update *update_time* and remain the *create_time*. so the problem is how to remain the *create_time*. 
  
  Next step is modify the domain case to implement this logic 


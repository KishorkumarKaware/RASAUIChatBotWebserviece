package com.chatbox.model;

public class JavaModel
{
    

    private String text;

    private Entities[] entities;

    private Intent intent;
    
    private String response_text;
    
    public String getResponse_text ()
    {
        return response_text;
    }

    public void setResponse_text (String response_text)
    {
        this.response_text = response_text;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public Entities[] getEntities ()
    {
        return entities;
    }

    public void setEntities (Entities[] entities)
    {
        this.entities = entities;
    }

    public Intent getIntent ()
    {
        return intent;
    }

    public void setIntent (Intent intent)
    {
        this.intent = intent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response_text = "+response_text+", text = "+text+", entities = "+entities+", intent = "+intent+"]";
    }
}
			
	
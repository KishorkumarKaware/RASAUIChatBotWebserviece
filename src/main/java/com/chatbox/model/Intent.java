package com.chatbox.model;

public class Intent
{
    private String name;

    private String confidence;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getConfidence ()
    {
        return confidence;
    }

    public void setConfidence (String confidence)
    {
        this.confidence = confidence;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", confidence = "+confidence+"]";
    }
}
			
	
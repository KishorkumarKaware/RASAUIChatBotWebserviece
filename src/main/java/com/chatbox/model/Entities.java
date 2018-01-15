package com.chatbox.model;

public class Entities
{
private String start;

private String extractor;

private String entity=null;

private String value;

private String[] processors;

private String end;

public String getStart ()
{
return start;
}

public void setStart (String start)
{
this.start = start;
}

public String getExtractor ()
{
return extractor;
}

public void setExtractor (String extractor)
{
this.extractor = extractor;
}

public String getEntity ()
{
return entity;
}

public void setEntity (String entity)
{
this.entity = entity;
}

public String getValue ()
{
return value;
}

public void setValue (String value)
{
this.value = value;
}

public String[] getProcessors ()
{
return processors;
}

public void setProcessors (String[] processors)
{
this.processors = processors;
}

public String getEnd ()
{
return end;
}

public void setEnd (String end)
{
this.end = end;
}

@Override
public String toString()
{
return "ClassPojo [start = "+start+", extractor = "+extractor+", entity = "+entity+", value = "+value+", processors = "+processors+", end = "+end+"]";
}
}


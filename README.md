# Starlight

Plugin loading made easy.

## What is possible?

Starlight can load plugins in the jar format. Those plugins can access every class from the parrent project which loads those plugins.

## Examples

### Plugin example:

```java
package gq.glowman554.plugin;

import gq.glowman554.starlight.annotations.StarlightEntry;
import gq.glowman554.starlight.data.PluginData;
import gq.glowman554.test.Test;

public class Entry
{
    @StarlightEntry(data = true)
    public void entry(PluginData data)
    {
        System.out.println("I am " + data.toString());
		}
}

```

### Loader example:

```java
package gq.glowman554.test;

import gq.glowman554.reflex.Reflex;
import gq.glowman554.starlight.Starlight;
import gq.glowman554.starlight.StarlightException;

public class Main
{
    public static void main(String[] args) throws StarlightException
    {
        Reflex.setDebug(true);
        Starlight.setDebug(true);
        
        Starlight s = new Starlight("plugins");
        s.load();
    }
}
```

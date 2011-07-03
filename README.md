Eclipse Fallback Plugin
=======================

This feature allows to define fallback rules for the folder trees of projects. These fallbacks will be visible in the resource navigator. Rules can be defined in an external yaml file.

Usage
-----

To use fallbacks in the resource navigator, you have to create a yaml file with fallback definitions.

An example:

```yaml
---
path: /Frontend/bm/de
fallbacks:
- /Frontend/bm/global
- /Frontend/default/de
- /Frontend/default/global
image: icons/bm_de.ico

---
path: /Frontend/bm/global
fallbacks:
- /Frontend/default/global
image: icons/bm.ico
```

The fallback file has to be selected in Eclipse: **Window -> Preferences -> Fallback Preferences**
After that, you can see the fallbacks in the *Fallback View* **(Window -> Show View)**

When you fold out the resource tree and reach a folder, which is defined as a path in the config, you will see all other files/folders, that are descendants of the related fallbacks.

License
-------

[Eclipse Public License 1.0](http://www.eclipse.org/legal/epl-v10.html)

SnakeYAML is released under the Apache 2.0 license


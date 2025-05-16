# Pyrefly for PyCharm

> [!NOTE]
> Disclaimer: This project is not affiliated with Meta/Facebook by any means.

[![Build](https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/build.yaml/badge.svg)][2]
[![Version](https://img.shields.io/jetbrains/plugin/v/26829)][3]
[![Rating](https://img.shields.io/jetbrains/plugin/r/rating/26829)][4]
[![Downloads](https://img.shields.io/jetbrains/plugin/d/26829)][5]

<!-- Plugin description -->
[Pyrefly][1] is a fast Python type checker and language server
developed by Meta.


## Usage

You must have Pyrefly installed to use this plugin.
It is available via PyPI:

```shell
$ pip install pyrefly
```

Additionally, if you are using IntelliJ IDEA or PyCharm Community Edition,
the <i>LSP4IJ</i> plugin must be installed and enabled.
Once it is installed, change the running mode to <i>LSP4IJ</i>.


## Logging

You are strongly encouraged to enable language server logging.
This will allow corresponding logs to be recorded in `idea.log`
for further analysis should a problem arises.

Add the following line to the <b>Help</b> |
<b>Diagnostic Tools</b> | <b>Debug Log Settings</b> panel:

```text
com.intellij.platform.lsp
```


  [1]: https://github.com/facebook/pyrefly
<!-- Plugin description end -->


## Installation

This plugin is [available on the Marketplace][3].
You can also download the ZIP files manually from [the <i>Releases</i> tab][6],
[the `build` branch][7] or [the <i>Actions</i> tab][8]
and follow the instructions described [here][9].

Currently supported versions:
2025.1 (build 251.23774.400) and later.


## Credits

Parts of this repository were taken or derived from:

* [@facebook/pyrefly][1]
* [@JetBrains/intellij-community][10]
* [@JetBrains/intellij-platform-plugin-template][11]


  [2]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/build.yaml
  [3]: https://plugins.jetbrains.com/plugin/26829/versions
  [4]: https://plugins.jetbrains.com/plugin/26829/reviews
  [5]: https://plugins.jetbrains.com/plugin/26829
  [6]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/releases
  [7]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/tree/build
  [8]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/build.yaml
  [9]: https://www.jetbrains.com/help/pycharm/managing-plugins.html#install_plugin_from_disk
  [10]: https://github.com/JetBrains/intellij-community
  [11]: https://github.com/JetBrains/intellij-platform-plugin-template

# Pyrefly for PyCharm

> [!NOTE]
> Disclaimer: This project is not affiliated with Meta/Facebook by any means.

[![Build](https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/build.yaml/badge.svg)][2]
[![Docs](https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/docs.yaml/badge.svg)][3]
[![Version](https://img.shields.io/jetbrains/plugin/v/26829)][4]
[![Rating](https://img.shields.io/jetbrains/plugin/r/rating/26829)][5]
[![Downloads](https://img.shields.io/jetbrains/plugin/d/26829)][6]

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

This plugin is [available on the Marketplace][4].
You can also download the ZIP files manually from [the <i>Releases</i> tab][7],
[the `build` branch][8] or [the <i>Actions</i> tab][9]
and follow the instructions described [here][10].

Currently supported versions:
2024.3.3 (build 243.24978.46) and later.


## Credits

Parts of this repository were taken or derived from:

* [@facebook/pyrefly][1]
* [@JetBrains/intellij-community][11]
* [@JetBrains/intellij-platform-plugin-template][12]


  [2]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/build.yaml
  [3]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/docs.yaml
  [4]: https://plugins.jetbrains.com/plugin/26829/versions
  [5]: https://plugins.jetbrains.com/plugin/26829/reviews
  [6]: https://plugins.jetbrains.com/plugin/26829
  [7]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/releases
  [8]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/tree/build
  [9]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/actions/workflows/build.yaml
  [10]: https://www.jetbrains.com/help/pycharm/managing-plugins.html#install_plugin_from_disk
  [11]: https://github.com/JetBrains/intellij-community
  [12]: https://github.com/JetBrains/intellij-platform-plugin-template

<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Changelog

This page documents user-facing changes.
For code changes, see [`CHANGELOG_CODE.md`][_-1].


  [_-1]: ./CHANGELOG_CODE.md


## [Unreleased]

<i>This section is currently empty.</i>


## [0.3.0] - 2025-09-03


### Removed

* 2025.2 and older are no longer supported.


## [0.2.0] - 2025-07-25

### Changed

* The language server icon now has more contrast in dark mode.
  Conversely, it will be worse in light mode.


### Fixed

* Previously, the plugin would not send the project's interpreter to Pyrefly,
  which might lead to unresolved reference errors
  if Pyrefly couldn't find the virtual environment on its own.
  This has been fixed.
* Prior to 2025.1.2, PyCharm did not support `textDocument/diagnostic`.
  2025.1.2 added support for it but did not enable pulling by default.
  The plugin will now always ask Pyrefly to use
  `textDocument/publishDiagnostics` regardless of IDE version.


### Removed

* 2024.3.3 and older are no longer supported.


## [0.1.1] - 2025-04-27

### Changed

* The plugin's logo has been updated.
* The plugin has been renamed to <i>Pyrefly (Unofficial)</i>.


## [0.1.0] - 2025-03-15

### Added

* Project initialized.


  [Unreleased]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/compare/v0.3.0..HEAD
  [0.3.0]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/compare/v0.2.0..v0.3.0
  [0.2.0]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/compare/v0.1.1..v0.2.0
  [0.1.1]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/compare/v0.1.0..v0.1.1
  [0.1.0]: https://github.com/InSyncWithFoo/pyrefly-for-pycharm/commits/v0.1.0

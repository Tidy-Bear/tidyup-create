# 熊氏补丁包：机械动力 （Tidy UP: Create）
Tidy 的 Create 及其常见附属 补丁模组，主要涉及最新版本中没有（或尚未发布）的一些修复。

## 功能特性

### # 修复：护目镜悬浮窗上的空格缩进

**仅客户端**

根据字体的空格宽度调节护目镜悬浮窗上的空格缩进量。如果你使用了与原版空格宽度不相等的字体，护目镜悬浮窗上会有文字错位的 bug。

在 Create 仓库中已修复，但尚未发布。（[Creators-of-Create/Create#6477](https://github.com/Creators-of-Create/Create/pull/6477)）

然而，许多附属 mod 在处理缩进时仍在使用早已被 Create 标为 deprecated 的过时 API，导致即便上述修复发布了，也无法作用于这些 mod。

因此，这个补丁做了 2 件事：

- 修复原生 bug，但 1.18.2 与 Modern UI 共同安装导致悬浮窗抖动的修复未实装
- 为常见的附属 mod 做适配，使修复起效

由于技术限制，使用 mixin 时必须提供精确的作用目标。目前已适配如下 mod：
- Create: Crafts & Additions （带发电的那个 additions ~~咋还没个译名~~）
- Create: Dreams & Desires （机械动力：梦想与欲望）
- Create Central Kitchen （机械动力：中央厨房，顺便修复了 [DragonsPlusMinecraft/CreateCentralKitchen#97](https://github.com/DragonsPlusMinecraft/CreateCentralKitchen/issues/97)）
- Create Enchantment Industry （机械动力：附魔工业）
- Create Ore Excavation （机械动力：矿石开掘）

这些 mod 已经审查过，无需进行额外适配：
- Create: Connected （机械动力：创意传动）
- Create: Copycats+ （更多伪装板！）
- Create: Ender Transmission （机械动力：末影传输）
- Create: Interiors
- Create: Misc & Things （机械动力：实用物件）
- Create: Steam 'n' Rails（机械动力：汽鸣铁道）
- Create: Stuff & Additions （机械动力：物品附加）
- Create Deco
- Create Guardian Beam Defense （守卫者激光防御塔）

假如仍然有没覆盖到的地方，可以尝试在配置文件中启用通用修复（config/craete_tidyspatches-client.toml）。
这项配置默认是关闭的，以节省内存与耗时。

**相关链接：**

https://github.com/BloCamLimb/ModernUI/issues/172

https://github.com/Creators-of-Create/Create/issues/6404

https://github.com/Creators-of-Create/Create/pull/6477

### # 修复：蓝图预览不消失

**仅客户端**

当蓝图从玩家热键栏移走时，让蓝图的预览能够成功消失。

在 Create 仓库中已修复，但尚未发布。（[Creators-of-Create/Create#6673](https://github.com/Creators-of-Create/Create/pull/6673)）。

这个补丁做了同样的事情，但用了与 Create 官方不同的做法。

**相关链接：**

https://github.com/Creators-of-Create/Create/issues/6195

https://github.com/Creators-of-Create/Create/pull/6673

### # 修复：上一份蓝图的预览仍然存在

**仅客户端**

bug 修复：切换到另一个未部署的蓝图物品后，上一份已部署的蓝图仍然会显示在坐标 (0, 0, 0) 处。

**相关链接：**

https://github.com/Creators-of-Create/Create/issues/6784

https://github.com/Creators-of-Create/Create/pull/6813

### # 改善：蓝图的 tooltip 上显示结构的尺寸大小

**仅客户端**

现在你可以在蓝图的 tooltip 上查看结构的尺寸大小（x, y, z）了。

### # 修复：存量转信器无法准确计算比原版更大的存量上限

**仅服务端**

让 存量转信器 在计算可用存量时能够正确识别比原版更大的存量上限。

在 Create 仓库中已修复，但尚未发布。
核心代码来自 [Creators-of-Create/Create@d9198f67](https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb)，作者 [zelophed](https://github.com/zelophed).

**相关链接：**

https://github.com/Creators-of-Create/Create/issues/5185

https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb


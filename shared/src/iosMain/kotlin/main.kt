import androidx.compose.ui.window.Application
import com.prasanth.composemultiplatapp.MainScreen
import kotlinx.cinterop.*
import platform.Foundation.NSStringFromClass
import platform.UIKit.*
import androidx.compose.ui.window.ComposeUIViewController

public fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("myApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(MainAppDelegate))
        }
    }
}

class MainAppDelegate @ObjCObjectBase.OverrideInit constructor() : UIResponder(), UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }
    override fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?, *>?
    ): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window?.rootViewController = ComposeUIViewController {
            MainScreen()
        }
        window?.makeKeyAndVisible()
        return true
    }
}

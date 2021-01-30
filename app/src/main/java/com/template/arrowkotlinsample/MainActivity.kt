package com.template.arrowkotlinsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import arrow.core.Either
import arrow.core.None
import arrow.core.Option
import arrow.core.Some

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testKotlinArrow()
    }

    /**
     * 実行結果:
     * 2020-05-07 22:09:25.826 25477-25477/com.template.arrowkotlinsample W/MainActivity: ----------- Option None/Some -----------
     * 2020-05-07 22:09:25.829 25477-25477/com.template.arrowkotlinsample W/MainActivity: 1 user is null
     * 2020-05-07 22:09:25.832 25477-25477/com.template.arrowkotlinsample W/MainActivity: 2 user name: abc
     * 2020-05-07 22:09:25.832 25477-25477/com.template.arrowkotlinsample W/MainActivity: ----------- Either -----------
     * 2020-05-07 22:09:25.838 25477-25477/com.template.arrowkotlinsample W/MainActivity: 3 right result: success
     * 2020-05-07 22:09:25.838 25477-25477/com.template.arrowkotlinsample W/MainActivity: 4 left result: 100
     */
    private fun testKotlinArrow() {
        Log.w(TAG, "----------- Option None/Some -----------")
        var user: Option<User> = None // None: 値がない
        user.fold({
            Log.w(TAG, "1 user is null")
        }, {
            Log.w(TAG, "1 user name: " + it.name)
        })

        user = Some(User("abc")) // Some: 値がある
        user.fold({
            Log.w(TAG, "2 user is null")
        }, {
            Log.w(TAG, "2 user name: " + it.name)
        })

        Log.w(TAG, "----------- Either -----------")
        // ArrowのEitherでは、成功時と失敗時の両方を表すことが可能.
        // leftとrightがあり、一般的には左側を異常値、右側を正常値として定義することが多い
        // HTTPリクエストの結果を代入するときなどに使う.
        val right: Either<Int, String> = Either.right("success")
        val left: Either<Int, String> = Either.left(100)
        right.fold({
            Log.w(TAG, "3 left result: $it")
        }, {
            Log.w(TAG, "3 right result: $it")
        })
        left.fold({
            Log.w(TAG, "4 left result: $it")
        }, {
            Log.w(TAG, "4 right result: $it")
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}

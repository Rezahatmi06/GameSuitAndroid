package com.example.gamesuitandroid.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.gamesuitandroid.enumm.PlayerSide
import com.example.gamesuitandroid.databinding.ActivityMainBinding
import com.example.gamesuitandroid.R
import com.example.gamesuitandroid.enumm.SuitCharacter
import usecase.SuitUseCase
import usecase.SuitUseCaseImpl

import kotlin.random.Random

class HomeActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding

    private var p1: Int = SuitCharacter.BATU.ordinal
    private var p2: Int = SuitCharacter.values()[Random.nextInt(0, 3)].ordinal
    private var isGameFinished: Boolean = false

    private lateinit var SuitUseCase: SuitUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SuitUseCaseImpl()

        bindviews()
        setState()
    }

    private fun setState() {
        Log.d("isGameFinished", "setState: " + isGameFinished.toString())

        if (!isGameFinished) {
            binding.flBtnLeftBatu.isEnabled = true
            binding.flBtnLeftGunting.isEnabled = true
            binding.flBtnLeftKertas.isEnabled = true
            setOnClickListener()
        } else {
            binding.flBtnLeftBatu.isEnabled = false
            binding.flBtnLeftGunting.isEnabled = false
            binding.flBtnLeftKertas.isEnabled = false
        }
    }

    private fun setPlayerMovement(
        playerSide: PlayerSide,
        suitCharacter: Int
    ) {
        Log.d("player", "setPlayerMovement: " + playerSide)
        Log.d("suit_character", "setPlayerMovement: " + SuitCharacter.values()[suitCharacter])

        if (playerSide == PlayerSide.PEMAIN1 && suitCharacter == SuitCharacter.BATU.ordinal) {
            binding.flBtnLeftBatu.setBackgroundColor(
                ContextCompat.getColor(
                    this,


                    R.color.purple_200
                )
            )
            binding.flBtnLeftGunting.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnLeftKertas.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
        } else if (playerSide == PlayerSide.PEMAIN1 && suitCharacter == SuitCharacter.GUNTING.ordinal) {
            binding.flBtnLeftBatu.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnLeftGunting.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple_200
                )
            )
            binding.flBtnLeftKertas.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
        } else if (playerSide == PlayerSide.PEMAIN1 && suitCharacter == SuitCharacter.KERTAS.ordinal) {
            binding.flBtnLeftBatu.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnLeftGunting.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnLeftKertas.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple_200
                )
            )
        } else if (playerSide == PlayerSide.PEMAIN2 && suitCharacter == SuitCharacter.BATU.ordinal) {
            binding.flBtnRightBatu.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple_200
                )
            )
            binding.flBtnRightGunting.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnRightKertas.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
        } else if (playerSide == PlayerSide.PEMAIN2 && suitCharacter == SuitCharacter.GUNTING.ordinal) {
            binding.flBtnRightBatu.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnRightGunting.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple_200
                )
            )
            binding.flBtnRightKertas.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
        } else if (playerSide == PlayerSide.PEMAIN2 && suitCharacter == SuitCharacter.KERTAS.ordinal) {
            binding.flBtnRightBatu.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnRightGunting.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            binding.flBtnRightKertas.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.purple_200
                )
            )
        }
    }

    private fun setOnClickListener() {
        binding.flBtnLeftBatu.setOnClickListener() {
            p1 = SuitCharacter.BATU.ordinal
            setPlayerMovement(PlayerSide.PEMAIN1, p1)
            setPlayerMovement(PlayerSide.PEMAIN2, p2)
            checkWinner()
            isGameFinished = true
            setState()
        }
        binding.flBtnLeftGunting.setOnClickListener() {
            p1 = SuitCharacter.GUNTING.ordinal
            setPlayerMovement(PlayerSide.PEMAIN1, p1)
            setPlayerMovement(PlayerSide.PEMAIN2, p2)
            checkWinner()
            isGameFinished = true
            setState()
        }
        binding.flBtnLeftKertas.setOnClickListener() {
            p1 = SuitCharacter.KERTAS.ordinal
            setPlayerMovement(PlayerSide.PEMAIN1, p1)
            setPlayerMovement(PlayerSide.PEMAIN2, p2)
            checkWinner()
            isGameFinished = true
            setState()
        }
        binding.fbtnRefresh.setOnClickListener {
            reset()
        }
    }

    private fun checkWinner() {
        when (SuitUseCase.decideWinner(this.p1, this.p2)) {
            SuitUseCaseImpl.SERI -> {
                binding.tvDraw.visibility = View.VISIBLE
                binding.ivVs.visibility = View.GONE
                binding.tvP1Win.visibility = View.GONE
                binding.tvP2Win.visibility = View.GONE
            }
            SuitUseCaseImpl.PEMAIN_SATU_MENANG -> {
                binding.tvP1Win.visibility = View.VISIBLE
                binding.ivVs.visibility = View.GONE
                binding.tvDraw.visibility = View.GONE
                binding.tvP2Win.visibility = View.GONE
            }
            SuitUseCaseImpl.PEMAIN_DUA_MENANG -> {
                binding.tvP2Win.visibility = View.VISIBLE
                binding.ivVs.visibility = View.GONE
                binding.tvDraw.visibility = View.GONE
                binding.tvP1Win.visibility = View.GONE
            }
        }
    }

    private fun reset() {
        isGameFinished = false
        binding.flBtnLeftBatu.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.flBtnLeftGunting.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.flBtnLeftKertas.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.flBtnRightBatu.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        binding.ivVs.visibility = View.VISIBLE
        binding.tvP1Win.visibility = View.GONE
        binding.tvP2Win.visibility = View.GONE
        binding.tvDraw.visibility = View.GONE
        binding.flBtnRightGunting.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        binding.flBtnRightKertas.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        setState()
    }

    private fun bindviews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}
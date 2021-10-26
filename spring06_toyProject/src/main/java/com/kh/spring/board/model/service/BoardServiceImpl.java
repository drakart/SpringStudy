package com.kh.spring.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.file.FileUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;

	public void insertBoard(List<MultipartFile> multiparts, Board board) {
		
		boardRepository.insertBoard(board);
		
		FileUtil util = new FileUtil();
		for (MultipartFile multipartFile : multiparts) {
			if(!multipartFile.isEmpty()) {
				boardRepository.insertFileInfo(util.fileUpload(multipartFile));
			}
		}
		
	}
	
	
}

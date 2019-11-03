<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="<c:url value="/admin-home"/>">Trang chủ</a></li>
					<li><a href="<c:url value="/admin-building?action=LIST"/>">Danh
							sách sản phẩm</a></li>
					<li><a href="#">Chỉnh sửa thông tin sản phẩm</a></li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Tên sản phẩm </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Người quản lý sản phẩm </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Quận </label>
										<div class="col-sm-3">
											<select class="form-control" id="form-field-select-1">
												<option value=""></option>
												<option value="AL">Alabama</option>
												<option value="AK">Alaska</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Phường </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Đường </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Kết cấu </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Số tầng hầm </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Diện tích sàn </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Hướng </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Hạng </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Diện tích thuê </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Mô tả diện tích </label>
										<div class="col-sm-10">
											<textarea class="form-control" id="form-field-8"
												placeholder="Default Text"
												style="margin: 0px 13.7875px 0px 0px; height: 200px;">
											</textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Giá thuê </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Mô tả giá </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Phí dịch vụ </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Phí ô tô </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Phí mô tô </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Phí ngoài giờ </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Tiền điện </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Đặt cọc </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Thanh toán </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Thời hạn thuê </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Thời gian trang trí </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Tên quản lý </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Số điện thoại quản lý </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Phí môi giới </label>
										<div class="col-sm-10">
											<input type="text" id="form-field-1-1"
												placeholder="Text Field" class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Loại sản phẩm </label>
										<div class="col-xs-12 col-sm-5">
											<div class="control-group">
												<div class="checkbox">
													<label> <input name="form-field-checkbox"
														type="checkbox" class="ace"> <span class="lbl">
															Tầng trệt</span>
													</label>
												</div>

												<div class="checkbox">
													<label> <input name="form-field-checkbox"
														type="checkbox" class="ace"> <span class="lbl">
															Nguyên căn</span>
													</label>
												</div>

												<div class="checkbox">
													<label> <input name="form-field-checkbox"
														type="checkbox" class="ace"> <span class="lbl">
															Nội thất</span>
													</label>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right"
											for="form-field-1-1"> Upload hình ảnh </label>
										<div class="col-xs-10">
											<label class="ace-file-input"><input type="file"
												id="id-input-file-2"><span
												class="ace-file-container" data-title="Choose"><span
													class="ace-file-name" data-title="No File ..."><i
														class=" ace-icon fa fa-upload"></i></span></span><a class="remove"
												href="#"><i class=" ace-icon fa fa-times"></i></a></label>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-offset-2 col-md-10	">
											<button class="btn btn-info" type="button">
												<i class="ace-icon fa fa-check bigger-110"></i> Thêm
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i> Hủy
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
</body>
</html>